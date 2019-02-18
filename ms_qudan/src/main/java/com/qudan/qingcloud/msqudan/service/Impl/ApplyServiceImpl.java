package com.qudan.qingcloud.msqudan.service.Impl;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.config.QudanConstant;
import com.qudan.qingcloud.msqudan.dao.PayOrderMapper;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.PosApplyExtMapper;
import com.qudan.qingcloud.msqudan.mymapper.TradeTypeMapper;
import com.qudan.qingcloud.msqudan.mymapper.UserShareQrCodeMapper;
import com.qudan.qingcloud.msqudan.mymapper.self.ApplyMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.ProductMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.UserMapperSelf;
import com.qudan.qingcloud.msqudan.mymapper.self.VipMapperSelf;
import com.qudan.qingcloud.msqudan.util.DateUtil;
import com.qudan.qingcloud.msqudan.util.RandomUtils;
import com.qudan.qingcloud.msqudan.util.requestBody.ApplyRB;
import com.qudan.qingcloud.msqudan.util.requestBody.PosApplyRB;
import com.qudan.qingcloud.msqudan.util.responses.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import scala.App;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class ApplyServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);

    @Autowired
    private ApplyMapperSelf applyMapperSelf;

    @Autowired
    private TradeTypeMapper tradeTypeMapper;

    @Autowired
    private ProductMapperSelf productMapperSelf;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CharacterServiceImpl characterService;

    @Autowired
    private RewordServiceImpl rewordService;

    @Autowired
    private UserShareQrCodeMapper userShareQrCodeMapper;

    @Autowired
    UserMapperSelf userMapperSelf;

    @Autowired
    PosApplyExtMapper posApplyExtMapper;

    @Autowired
    PayOrderMapper payOrderMapper;

    @Autowired
    VipMapperSelf vipMapperSelf;



    @Transactional
    public Map<String,Object> settlement(ApiResponseEntity ARE, Integer applyId){

        //TODO POS金额结算 ， 是否有代理，奖励记录是否要体现TYPE=7 的金额
        log.info("applyid:"+applyId);
        Apply apply = applyMapperSelf.selectByPrimaryKey(applyId);
        if(apply == null){
            ARE.addInfoError("apply.isEmpty", "申请记录不存在");
            return null;
        }
        if(apply.getIsSettle() == 1){
            ARE.addInfoError("apply.isSettle", "团队佣金和完成任务奖励已结算");
            return null;
        }
        User user = userService.getUserById(apply.getUserId());
        if(user == null){
            log.info("用户为空");
        }
        log.info("apply.userid:"+apply.getUserId());
        User shareUser = null;
        User inviteUser = null;
        User userDL = null;
        String code = apply.getInviteCode();
        Product product = productMapperSelf.selectByPrimaryKey(apply.getProductId());
        if(product == null){
            ARE.addInfoError("apply.product.isEmpty", "apply.product.isEmpty");
            return null;
        }
        log.info("apply.productid:"+product.getId());


        if(StringUtils.isNotBlank(code)){
            Integer shareUserId = QudanHashIdUtils.decodeHashId(code);
            if(shareUserId != null){
                shareUser = userService.getUserById(shareUserId);
            }
        }

        if(user != null && user.getRecommendInviteId() != null){
            inviteUser = userService.getUserById(user.getRecommendInviteId());
        }

        if(inviteUser != null && inviteUser.getRecommendInviteId() != null){
            userDL = userService.getUserById(inviteUser.getRecommendInviteId());
        }

        if(shareUser != null && shareUser.getRecommendInviteId() != null){
            userDL = userService.getUserById(shareUser.getRecommendInviteId());
        }

        //是否是点分享链接的
        boolean isShare = shareUser != null;
        //是否有邀请人
        boolean isInvite = inviteUser != null;
        //是否有代理链条
        boolean isDL = userDL != null;
        log.info("isShare:" + isShare + ", isInvite:" + isInvite + "， isDL:"  + isDL);
        log.info("shareid:" + (isShare? shareUser.getId():"null") + ", inviteid:" + (isInvite? inviteUser.getId():"null") + "， dlid:"  + (isDL?userDL.getId():"null"));

        AgentConfig agentConfig = null;
        VipConfig vipConfig = null;
        VipConfig userVipConfig = null;
        if(isDL){
            agentConfig = characterService.getAgentByUserId(userDL.getId());
        }

        //如果邀请人和 分享人都存在， 邀请人和分享人必须为同一个人， 才能形成代理链条
        if(isInvite && isShare && !inviteUser.getId().equals(shareUser.getId())){
            log.info("邀请人和 分享人都存在， 邀请人和分享人必须为同一个人， 才能形成代理链条!");
            isDL = false;
        }
        if(isDL && isShare && shareUser.getAgentLevel() != null && shareUser.getAgentLevel() > 0){
            log.info("有代理链条，但是中间人shareUser是代理，也不能形成代理链条");
            isDL = false;
        }
        if(isDL && isInvite && inviteUser.getAgentLevel() != null && inviteUser.getAgentLevel() > 0){
            log.info("有代理链条，但是中间人inviteUser是代理，也不能形成代理链条");
            isDL = false;
        }

        if(isShare){
            vipConfig = characterService.getVipByUserId(shareUser.getId());
        }


        BigDecimal basePrice = rewordService.getBasePrice(product);
        BigDecimal platformPrice = product.getProductType() == 3? product.getPlatformAward() : null;
        BigDecimal vipPrice = null;
        BigDecimal agentPrice = null;
        BigDecimal userVipPrice = null;
        UserAccount shareAccount = null;
        UserAccount dlAccount = null;
        UserAccount userAccount = null;

        log.info("apply.basePrice:"+basePrice.toString());
        if(isShare){
            vipPrice = vipConfig == null? null:basePrice.multiply(vipConfig.getAddRate());
            shareAccount = userService.getUserAccountByUserId(shareUser.getId());
        }
        if(isDL){
            agentPrice = basePrice.multiply(agentConfig.getDirectRate());
            dlAccount = userService.getUserAccountByUserId(shareUser.getId());
        }
        if(user != null){
            userAccount = userService.getUserAccountByUserId(user.getId());
            userVipConfig = characterService.getVipByUserId(user.getId());
            if(userVipConfig != null){
                userVipPrice = basePrice.multiply(userVipConfig.getAddRate());
            }
        }

        TradeType taskTrade = null; //任务奖励记录
        TradeType teamTrade = null; //分佣记录

        Date date =  new Date();
        if(isDL && isInvite && isShare){
            //业绩人 inviteUser,也是 shareUser
            //完成任务奖励人 inviteUser,也是 shareUser
            //分用人 userDL
            taskTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TASK_REWORD.getType(),
                    date,
                    basePrice,
                    inviteUser.getId()//受益人
            );
            taskTrade.setAccount(shareAccount.getId());
            if(vipConfig != null){
                taskTrade.setVipPrice(vipPrice);
                taskTrade.setVipLevel(vipConfig.getVipLevel().shortValue());
                taskTrade.setVipRate(vipConfig.getAddRate());
                taskTrade.setPrice(vipPrice.add(basePrice));
            } else {
                taskTrade.setPrice(basePrice);
            }
            if(platformPrice != null) {
                taskTrade.setPlatformPrice(platformPrice);
                taskTrade.setPrice(taskTrade.getPrice().add(platformPrice));
            }


            teamTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TEAM_REWORD.getType(),
                    date,
                    basePrice,
                    userDL.getId()//受益人
            );
            teamTrade.setAccount(dlAccount.getId());
            teamTrade.setAgentLevel(agentConfig.getLevel());
            teamTrade.setAgentRate(agentConfig.getDirectRate());
            teamTrade.setRelationUserId(inviteUser.getId());//业绩人
            teamTrade.setPrice(agentPrice);
        }

        if(isDL && isInvite && !isShare){
            //业绩人 inviteUserId
            //完成任务奖励人 user
            //分用人 userDL
            taskTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TASK_REWORD.getType(),
                    date,
                    basePrice,
                    user.getId()//受益人
            );
            taskTrade.setAccount(userAccount.getId());
            if(userVipConfig != null){
                taskTrade.setVipPrice(userVipPrice);
                taskTrade.setVipLevel(userVipConfig.getVipLevel().shortValue());
                taskTrade.setVipRate(userVipConfig.getAddRate());
                taskTrade.setPrice(userVipPrice.add(basePrice));
            } else {
                taskTrade.setPrice(basePrice);
            }
            if(platformPrice != null) {
                taskTrade.setPlatformPrice(platformPrice);
                taskTrade.setPrice(taskTrade.getPrice().add(platformPrice));
            }

            teamTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TEAM_REWORD.getType(),
                    date,
                    basePrice,
                    userDL.getId()//受益人
            );
            teamTrade.setAccount(dlAccount.getId());
            teamTrade.setAgentLevel(agentConfig.getLevel());
            teamTrade.setAgentRate(agentConfig.getDirectRate());
            teamTrade.setRelationUserId(inviteUser.getId());//业绩人
            teamTrade.setPrice(agentPrice);
        }

        if(isDL && !isInvite && isShare){
            //业绩人 shareUserId
            //完成任务奖励人 shareUserId
            //分用人 userDL
            taskTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TASK_REWORD.getType(),
                    date,
                    basePrice,
                    shareUser.getId()//受益人
            );
            taskTrade.setAccount(shareAccount.getId());
            if(vipConfig != null){
                taskTrade.setVipPrice(vipPrice);
                taskTrade.setVipLevel(vipConfig.getVipLevel().shortValue());
                taskTrade.setVipRate(vipConfig.getAddRate());
                taskTrade.setPrice(vipPrice.add(basePrice));
            } else {
                taskTrade.setPrice(basePrice);
            }
            if(platformPrice != null) {
                taskTrade.setPlatformPrice(platformPrice);
                taskTrade.setPrice(taskTrade.getPrice().add(platformPrice));
            }

            teamTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TEAM_REWORD.getType(),
                    date,
                    basePrice,
                    userDL.getId()//受益人
            );
            teamTrade.setAccount(dlAccount.getId());
            teamTrade.setAgentLevel(agentConfig.getLevel());
            teamTrade.setAgentRate(agentConfig.getDirectRate());
            teamTrade.setRelationUserId(shareUser.getId());//业绩人
            teamTrade.setPrice(agentPrice);
        }

        if(!isDL && isShare ){
            //业绩人 没有
            //完成任务奖励人 shareUser
            //分佣人 没有
            taskTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TASK_REWORD.getType(),
                    date,
                    basePrice,
                    shareUser.getId()//受益人
            );
            taskTrade.setAccount(shareAccount.getId());
            taskTrade.setPrice(basePrice);
            if(vipConfig != null){
                taskTrade.setVipPrice(vipPrice);
                taskTrade.setVipLevel(vipConfig.getVipLevel().shortValue());
                taskTrade.setVipRate(vipConfig.getAddRate());
                taskTrade.setPrice(vipPrice.add(basePrice));
            } else {
                taskTrade.setPrice(basePrice);
            }
            if(platformPrice != null) {
                taskTrade.setPlatformPrice(platformPrice);
                taskTrade.setPrice(taskTrade.getPrice().add(platformPrice));
            }
        }

        if(!isDL && !isShare){
            if(user == null){
                ARE.addInfoError("userAndShare.isEmpty", "没有分享人也没有user情况是不存在的请检查数据");
                return null;
            }
            //业绩人 没有
            //完成任务奖励人 user
            //分佣人 没有
            taskTrade = createTradeByApply(apply,
                    QudanConstant.TRADE_TYPE.TASK_REWORD.getType(),
                    date,
                    basePrice,
                    user.getId()//受益人
            );
            taskTrade.setAccount(userAccount.getId());
            taskTrade.setPrice(basePrice);
            if(userVipConfig != null){
                taskTrade.setVipPrice(userVipPrice);
                taskTrade.setVipLevel(userVipConfig.getVipLevel().shortValue());
                taskTrade.setVipRate(userVipConfig.getAddRate());
                taskTrade.setPrice(userVipPrice.add(basePrice));
            } else {
                taskTrade.setPrice(basePrice);
            }
            if(platformPrice != null) {
                taskTrade.setPlatformPrice(platformPrice);
                taskTrade.setPrice(taskTrade.getPrice().add(platformPrice));
            }
        }

        if(taskTrade != null){
            tradeTypeMapper.insertSelective(taskTrade);
        }
        if(teamTrade != null){
            tradeTypeMapper.insertSelective(teamTrade);
        }
        Apply apply_update = new Apply();
        apply_update.setId(apply.getId());
        apply_update.setOfficialStatus(2);
        apply_update.setStatus(2);
        apply_update.setIsSettle(1);
        if(product.getProductType() == 1){
            apply_update.setOfficialLimit(new BigDecimal(200000));
        } else if(product.getProductType() == 2){
            apply_update.setOfficialLimit(new BigDecimal(product.getAmountLine().toString()));
            apply_update.setOfficialExpire("12期");
        }
        apply_update.setOfficialTime(date);
        applyMapperSelf.updateByPrimaryKeySelective(apply_update);
        return null;
    }

    @HystrixCommand
    public Map<String,Object> loanApply(ApiResponseEntity ARE, ApplyRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(checkApplyRB(ARE, RB)){
            createByRB(RB, ARE.getUserId(),data);
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> posApply(ApiResponseEntity ARE,PosApplyRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(checkPosApply(ARE, RB)){
            PosApplyExt ext = createPosApply(RB, ARE.getUserId(), data);
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> posApplyStatus(ApiResponseEntity ARE,String extIdStr){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(extIdStr)){
           ARE.addInfoError("extId.isEmpty", "extId不能为空");
           return null;
        }
        Integer extId = QudanHashId12Utils.decodeHashId(extIdStr);
        if(extId == null){
            ARE.addInfoError("extId.isError", "不是正确的extId");
            return null;
        } else {
            extId = extId-4000;
        }
        PayOrder result = null;
        PayOrder payAlready = applyMapperSelf.existAlreadyPayPosOrder(extId);
        PayOrder payOrder = applyMapperSelf.getPosOrderStatus(extId);
        if(payAlready != null){
            result = payAlready;
        } else{
            result = payOrder;
        }

        if(result == null){
            ARE.addInfoError("extId.isNotExist", "不存在的extId");
            return null;
        }
        String orderStatus = result.getOrderStatus();
        data.put("orderStatus", orderStatus);
        data.put("type", null);
        data.put("orderNo", null);
        data.put("price", null);
        if(orderStatus.equals("1")){
            data.put("type", result.getType());
            data.put("orderNo", result.getOrderId());
            data.put("price", result.getTotalFee());
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> cardApply(ApiResponseEntity ARE, ApplyRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(checkApplyRB(ARE, RB)){
            createByRB(RB, ARE.getUserId(), data);
        }
        return data;
    }


    private boolean checkPosApply(ApiResponseEntity ARE,PosApplyRB RB){
        if(StringUtils.isBlank(RB.getAddress())){
            ARE.addInfoError("address.isEmpty", "收货地址不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getApplyMobile())){
            ARE.addInfoError("applyMobile.isEmpty", "申请手机号不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getApplyName())){
            ARE.addInfoError("applyName.isEmpty", "申请人不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getReceiver())){
            ARE.addInfoError("receiver.isEmpty", "收货人不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getReceiverMobile())){
            ARE.addInfoError("receiverMobile.isEmpty", "收货收机不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getRegion())){
            ARE.addInfoError("region.isEmpty", "地址不能不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getValidcode())){
            ARE.addInfoError("validcode.isEmpty", "验证码不能为空");
            return false;
        }
        if(RB.getProductId() == null){
            ARE.addInfoError("productId.isEmpty", "产品ID不能为空");
            return false;
        }
        Product product = productMapperSelf.selectByPrimaryKey(RB.getProductId());
        if(product == null){
            ARE.addInfoError("product.isNotExist", "不存在的产品Id");
            return false;
        }
        return userService.checkCode(ARE, RB.getApplyMobile(), RB.getValidcode(), 4, true);
    }

    private boolean checkApplyRB(ApiResponseEntity ARE,ApplyRB RB){
        if(StringUtils.isBlank(RB.getIdNo())){
            ARE.addInfoError("idNo.isEmpty", "身份证号不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("mobile.isEmpty", "手机号不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getValidcode())){
            ARE.addInfoError("validcode.isEmpty", "验证码不能为空");
            return false;
        }
        if(StringUtils.isBlank(RB.getName())){
            ARE.addInfoError("name.isEmpty", "用户姓名不能能为空");
            return false;
        }
        if(RB.getProductId() == null){
            ARE.addInfoError("product.isEmpty", "产品id不能为空");
            return false;
        }
        if(ARE.getUserId() != null){
            User user = userService.getUserById(ARE.getUserId());
            if(user.getStatus() == null || user.getStatus() != 3){
                ARE.addInfoError("status.isEmpty", "未实名或者审核未通过");
                return false;
            }
        }

        Product product = productMapperSelf.selectByPrimaryKey(RB.getProductId());
        if(product == null){
            ARE.addInfoError("product.isNotExist", "不存在的产品Id");
            return false;
        }
        Apply apply = applyMapperSelf.selectApplyIdNo( RB.getIdNo(), RB.getProductId());
        if(apply != null){
            ARE.addInfoError("apply.isExist", "身份证已申请过"+(product.getProductType()==1?"该信用卡":"贷款"));
            return false;
        }
        return userService.checkCode(ARE, RB.getMobile(), RB.getValidcode(), 4, true);
    }

    private PosApplyExt createPosApply(PosApplyRB  RB, Integer userId, Map<String,Object> data){
        Date date = new Date();
        PosApplyExt ext = new PosApplyExt();
        BeanUtils.copyProperties(RB, ext);
        ext.setRebackAlipayAccount(RB.getAlipayAcount());
        ext.setUserId(userId);
        ext.setCreateTime(date);
        if(StringUtils.isNotBlank(RB.getShareid())){
            User inviteUser = null;
            Integer qrcodeId = QudanHashId10Utils.decodeHashId(RB.getShareid());
            log.info("===================qrcodeId:"+ qrcodeId +" --------------------------------");
            if(qrcodeId == null){
                log.info("===================shareid:"+ RB.getShareid() +" 无效--------------------------------");
            } else {
                UserShareQrCode qrCode = userShareQrCodeMapper.selectByPrimaryKey(qrcodeId);
                if(qrCode == null){
                    log.info("===================qrcodeId:"+ qrcodeId +" 无效--------------------------------");
                } else {
                    if(qrCode.getPid() == null || qrCode.getPid().intValue() != RB.getProductId()){
                        log.info("===================qrcodeId.pid:"+ qrCode.getPid() + ";  pid无效--------------------------------");
                    } else {
                        inviteUser = userMapperSelf.selectById(qrCode.getUserId());
                        if(inviteUser != null){
                            ext.setInviteCode(inviteUser.getInviteCode());
                        } else {
                            log.info("===================inviteUserId:"+ qrCode.getUserId() +" 无效--------------------------------");
                        }
                    }
                }
            }
        }
        Product product = productMapperSelf.selectByPrimaryKey(RB.getProductId());
        BigDecimal total = product.getPosDeposit().add(product.getPosPrice());
        data.put("deposit", total.toString());
        //保证金， 支付金额
        ext.setPayPrice(total);
        ext.setPayDeposit(product.getPosDeposit());
        posApplyExtMapper.insertSelective(ext);
        data.put("extId", QudanHashId12Utils.encodeHashId(ext.getId()+4000));
        return ext;
    }


    public boolean callBackPosApplyTest(Integer extId){
        PosApplyExt ext = posApplyExtMapper.selectByPrimaryKey(extId);
        PayOrder payOrder = applyMapperSelf.getPosOrderStatus(extId);
        if(payOrder == null){
            payOrder = new PayOrder();
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
            String out_trade_no = dateFormat.format(now);
            payOrder.setOrderId(out_trade_no);
            //微信支付交易类型
            payOrder.setTradeType("NATIVE");
            //支付状态(待支付:0)
            payOrder.setOrderStatus("0");
            //1:微信支付，2:支付宝支付
            payOrder.setType("1");
            //用户id
            payOrder.setUserId(ext.getUserId()==null?"":ext.getUserId().toString());
            //支付金额(单位：分)
            payOrder.setTotalFee("0.01");
            //微信支付 prepay_id
            payOrder.setPrepayId("wxmn"+ RandomUtils.generateNumString(32));
            //交易时间
            payOrder.setTradeTime(now);
            payOrder.setExtId(extId);
            payOrderMapper.insert(payOrder);
        }
        applyMapperSelf.updatePayOrderStatus(payOrder.getOrderId());
        return callBackPosApply(extId+4000, payOrder.getOrderId());
    }

    public boolean callBackPosApply(String extIdStr, String payOrderNo){
        Integer extId = QudanHashId12Utils.decodeHashId(extIdStr);
        return callBackPosApply(extId, payOrderNo);
    }
    /**
     * 支付成功后的回调
     * @param extId
     * @param payOrderNo
     * @return
     */
    public boolean callBackPosApply(Integer extId, String payOrderNo){
        if(extId != null){
            extId = extId - 4000;
            PosApplyExt ext = posApplyExtMapper.selectByPrimaryKey(extId);
            if(ext != null){
                Apply apply = new Apply();
                Date date = new Date();
                String dayStr = DateUtil.getFormatDate(date, "yyyyMMdd");
                dayStr = dayStr.substring(2, dayStr.length());
                apply.setUserId(ext.getUserId());
                apply.setProductId(ext.getProductId());
                apply.setCreateTime(date);
                apply.setModifyTime(date);
                apply.setMobile(ext.getApplyMobile());
                apply.setName(ext.getApplyName());
                apply.setStatus(1);
                apply.setOfficialStatus(0);
                apply.setIsSettle(0);
                apply.setLastOfficialQuery(null);
                apply.setRejectReason(null);
                apply.setInviteCode(ext.getInviteCode());
                applyMapperSelf.insertSelective(apply);
                Apply apply_update = new Apply();
                apply_update.setId(apply.getId());
                apply_update.setApplyIdCode(QudanHashIdUtils.encodeHashId(apply.getId()));
                Integer lastIdOfCurrentDay = applyMapperSelf.selectLast5Apply("QD"+dayStr+"0001");
                Integer sub = 1;
                if(lastIdOfCurrentDay != null){
                    sub = (sub + apply.getId()-lastIdOfCurrentDay);
                }
                String code = "";
                if(sub < 10){
                    code = "000" + sub;
                } else if(sub >= 10 && sub < 100){
                    code = "00" + sub;
                } else if(sub >= 100 && sub < 1000){
                    code = "0" + sub;
                } else {
                    code = sub + "";
                }
                apply_update.setApplyIdCode("QD"+dayStr + code);
                applyMapperSelf.updateByPrimaryKeySelective(apply_update);
                PayOrder payOrder = vipMapperSelf.getFeeByOrderId(payOrderNo);
                PosApplyExt ext_update = new PosApplyExt();
                ext_update.setId(ext.getId());
                ext_update.setPayOrderNo(payOrderNo);
                //支付类型
                ext_update.setPayType(Integer.valueOf(payOrder.getType()));
                ext_update.setDepositStatus(1);
                ext_update.setDeliverStatus(1);
                ext_update.setApplyId(apply.getId());
                ext_update.setModifyTime(date);
                posApplyExtMapper.updateByPrimaryKeySelective(ext_update);
            }
        } else {
            log.info("---------------------不存在的EXT信息["+extId+"]");
        }
        return false;
    }

    private Apply createByRB(ApplyRB RB, Integer userId, Map<String,Object> data){
        Date date = new Date();
        String dayStr = DateUtil.getFormatDate(date, "yyyyMMdd");
        dayStr = dayStr.substring(2, dayStr.length());
        Apply apply = new Apply();
        apply.setUserId(userId);
        apply.setProductId(RB.getProductId());
        apply.setCreateTime(date);
        apply.setModifyTime(date);
        apply.setMobile(RB.getMobile());
        apply.setName(RB.getName());
        apply.setIdNo(RB.getIdNo());
        apply.setStatus(1);
        apply.setOfficialStatus(0);
        apply.setIsSettle(0);
        apply.setLastOfficialQuery(null);
        apply.setRejectReason(null);
        apply.setInviteCode(RB.getInviteCode());
        log.info("===================shareid:"+ RB.getShareid() +"--------------------------------");
        log.info("===================productId:"+ RB.getProductId() +"--------------------------------");
        if(StringUtils.isNotBlank(RB.getShareid())){
            User inviteUser = null;
            Integer qrcodeId = QudanHashId10Utils.decodeHashId(RB.getShareid());
            log.info("===================qrcodeId:"+ qrcodeId +" --------------------------------");
            if(qrcodeId == null){
                log.info("===================shareid:"+ RB.getShareid() +" 无效--------------------------------");
            } else {
                UserShareQrCode qrCode = userShareQrCodeMapper.selectByPrimaryKey(qrcodeId);
                if(qrCode == null){
                    log.info("===================qrcodeId:"+ qrcodeId +" 无效--------------------------------");
                } else {
                    if(qrCode.getPid() == null || qrCode.getPid().intValue() != RB.getProductId()){
                        log.info("===================qrcodeId.pid:"+ qrCode.getPid() + ";  pid无效--------------------------------");
                    } else {
                        inviteUser = userMapperSelf.selectById(qrCode.getUserId());
                        if(inviteUser != null){
                            apply.setInviteCode(inviteUser.getInviteCode());
                        } else {
                            log.info("===================inviteUserId:"+ qrCode.getUserId() +" 无效--------------------------------");
                        }
                    }
                }
            }
        }
        applyMapperSelf.insertSelective(apply);
        Apply apply_update = new Apply();
        apply_update.setId(apply.getId());
        apply_update.setApplyIdCode(QudanHashIdUtils.encodeHashId(apply.getId()));
        Integer lastIdOfCurrentDay = applyMapperSelf.selectLast5Apply("QD"+dayStr+"0001");
        Integer sub = 1;
        if(lastIdOfCurrentDay != null){
            sub = (sub + apply.getId()-lastIdOfCurrentDay);
        }
        String code = "";
        if(sub < 10){
            code = "000" + sub;
        } else if(sub >= 10 && sub < 100){
            code = "00" + sub;
        } else if(sub >= 100 && sub < 1000){
            code = "0" + sub;
        } else {
            code = sub + "";
        }
        apply_update.setApplyIdCode("QD"+dayStr + code);
        applyMapperSelf.updateByPrimaryKeySelective(apply_update);
        ProductSimple productSimple = productMapperSelf.selectSimpleByProductId(apply.getProductId());
        data.put("productLink", productSimple.getProductLink());
        return apply;
    }

    //根据申请记录创建交易记录
    public TradeType createTradeByApply(Apply apply,
                                         Integer type,
                                         Date date,
                                         BigDecimal basePrice,
                                         Integer userId){
        TradeType tradeType = new TradeType();
        tradeType.setApplyId(apply.getId());
        tradeType.setTradeType(type);
        tradeType.setCreateTime(date);
        tradeType.setModifyTime(date);
        tradeType.setStatus(1);
        if(type == QudanConstant.TRADE_TYPE.TI_XIAN.getType()){
            tradeType.setIndirectType(null);
            tradeType.setPrice(basePrice);
        } else if(type == QudanConstant.TRADE_TYPE.TASK_REWORD.getType()){
            tradeType.setIndirectType(null);
        } else if(type == QudanConstant.TRADE_TYPE.TEAM_REWORD.getType()){
            tradeType.setIndirectType(3);
        } else if(type == QudanConstant.TRADE_TYPE.VIP_PAY.getType()){
            tradeType.setIndirectType(null);
            tradeType.setPrice(basePrice);
        } else if(type == QudanConstant.TRADE_TYPE.JITI_REWORD.getType()){
            tradeType.setIndirectType(null);
            tradeType.setPrice(basePrice);
        }
        tradeType.setSendStatus(1);
        tradeType.setBasePrice(basePrice);
        tradeType.setUserId(userId);
        return tradeType;
    }
}
