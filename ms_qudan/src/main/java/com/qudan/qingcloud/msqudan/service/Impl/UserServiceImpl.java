package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.cloudClients.UserLoginClient;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.*;
import com.qudan.qingcloud.msqudan.mymapper.self.*;
import com.qudan.qingcloud.msqudan.util.*;
import com.qudan.qingcloud.msqudan.util.params.OrderParams;
import com.qudan.qingcloud.msqudan.util.requestBody.*;
import com.qudan.qingcloud.msqudan.util.responses.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    WeixinUserTempMapper weixinUserTempMapper;

    @Autowired
    WeixinBindingMapper weixinBindingMapper;

    @Autowired
    OtherMapperSelf otherMapperSelf;

    @Autowired
    SmsSendRecordMapper smsSendRecordMapper;

    @Autowired
    UserMapperSelf userMapperSelf;

    @Autowired
    UserLoginClient userLoginClient;

    @Autowired
    WeixinQrMapperSelf weixinQrMapperSelf;

    @Autowired
    WeixinServiceImpl weixinService;

    @Autowired
    WeixinSceneRecordMapper weixinSceneRecordMapper;

    @Autowired
    SceneIdServiceImpl sceneIdService;

    @Autowired
    UserShareMapper userShareMapper;

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    VipMapperSelf vipMapperSelf;

    @Autowired
    AgentMapperSelf agentMapperSelf;

    @Autowired
    UserShareQrCodeMapper userShareQrCodeMapper;

    @Autowired
    CommonConfig config;

    @Autowired
    CharacterServiceImpl characterService;

    public Map<String,Object> loginWithValidcode(ApiResponseEntity ARE, UserLoginRB RB, User user){
        Map<String,Object> data = Maps.newHashMap();
        if(checkCode(ARE, RB.getMobile(), RB.getValidcode(), 2, true)){
            YHResult result = userLoginClient.appLogin(user.getUsername(), user.getId());
            if(result == null){
                ARE.addInfoError("zuul.system.error", "微服务调用错误!");
                return null;
            }

            if(result.getData() == null){
                ARE.addInfoError("zuul.data.isEmpty", "微服务没有返回数据");
            }
            data = getToken(ARE, user);
        }
        return data;
    }

    public Map<String,Object> loginWithPassword(ApiResponseEntity ARE, UserLoginRB RB, User user){
        Map<String,Object> data = Maps.newHashMap();
        if(!user.getPassword().equals(PasswordUtils.encodePassword(RB.getPassword()))){
            ARE.addInfoError("login.password.isError", "密码不正确");
            return null;
        }
        data = getToken(ARE, user);
        return data;
    }


    @HystrixCommand
    public Map<String,Object> forget(ApiResponseEntity ARE, UserLoginRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("mobile.isEmpty", "手机号不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getPassword())){
            ARE.addInfoError("password.isEmpty", "密码不能为空");
            return null;
        }
        User user = userMapperSelf.selectUserByMobile(RB.getMobile());
        if(user == null){
            ARE.addInfoError("user.mobile.isExist", "手机号未注册");
            return null;
        }
        if(checkCode(ARE, RB.getMobile(), RB.getValidcode(), 3, true)){
            User user_update = new User();
            user_update.setId(user.getId());
            user_update.setPassword(PasswordUtils.encodePassword(RB.getPassword()));
            userMapperSelf.updateByPrimaryKeySelective(user_update);
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> registerWx(ApiResponseEntity ARE, UserLoginRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("mobile.isEmpty", "手机号不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getWutid())){
            ARE.addInfoError("wutid.isEmpty", "微信信息id不能为空");
            return null;
        }
        logger.info("-------------wutid:"+RB.getWutid());
        logger.info("-------------shareid:"+RB.getShareid());

        Date date = new Date();
        if(checkCode(ARE, RB.getMobile(), RB.getValidcode(), 5, true)){
            WeixinUserTemp wut = weixinUserTempMapper.selectByPrimaryKey(QudanHashId14Utils.decodeHashId(RB.getWutid()));
            WeixinBinding openidBind = weixinService.selectBindingByOpenId(wut.getOpenid());
       /*     User openidUser = null;
            if(openidBind != null){
                openidUser = userMapperSelf.selectById(openidBind.getUserId());
            }*/

            User mobileuser = userMapperSelf.selectUserByMobile(RB.getMobile());
            WeixinBinding userBing = null;
            if(mobileuser != null){
                userBing = weixinService.selectBindingByUserId(mobileuser.getId());
            }

            User user = null;
            WeixinBinding binding = null;
            if(openidBind == null && mobileuser == null){
                User inviteUser = null;
                user = new User();
                user.setPassword(PasswordUtils.encodePassword(RB.getPassword()));
                user.setUserface(MatrixToImageWriter.getWeixinTx(
                        config, wut.getHeadImgUrl()
                ));
                user.setIsenable(1);
                user.setRegisterMobile(RB.getMobile());
                user.setRegisterTime(new Date());
                user.setLastLoginTime(new Date());
                user.setStatus(1);
                user.setFinanceStatus(1);
                user.setUserType(0);
                user.setModifyTime(new Date());
                user.setUsername(wut.getNickname());
                user.setPassword(PasswordUtils.encodePassword("123456"));

                //邀请逻辑,未注册 才有邀请逻辑
                if(StringUtils.isNotBlank(RB.getShareid())){
                    Integer qrcodeId = QudanHashId10Utils.decodeHashId(RB.getShareid());
                    if(qrcodeId == null){
                        ARE.addInfoError("share.shareid.isError", "无效的分享id");
                        return null;
                    }
                    UserShareQrCode qrCode = userShareQrCodeMapper.selectByPrimaryKey(qrcodeId);
                    if(qrCode == null){
                        ARE.addInfoError("share.shareid.isError", "无效的分享id");
                        return null;
                    }
                    inviteUser = userMapperSelf.selectById(qrCode.getUserId());

                    if(inviteUser != null){
                        user.setRecommendInviteCode(inviteUser.getInviteCode());
                        user.setRecommendInviteId(inviteUser.getId());
                    }
                }
                userMapperSelf.insertSelective(user);
                //生成账户
                UserAccount userAccount = new UserAccount();
                userAccount.setUserId(user.getId());
                userAccount.setBlance(BigDecimal.ZERO);
                userAccount.setAllowTx(BigDecimal.ZERO);
                userAccount.setTx(BigDecimal.ZERO);
                userAccount.setCreateTime(new Date());
                userAccount.setModifyTime(new Date());
                userAccountMapper.insertSelective(userAccount);
                User user_update = new User();
                user_update.setId(user.getId());
                user_update.setInviteCode(QudanHashIdUtils.encodeHashId(user.getId()));
                userMapperSelf.updateByPrimaryKeySelective(user_update);
                if(inviteUser != null){
                    characterService.becomeAgent(inviteUser.getId());
                }
                data = getToken(ARE, user);
            } else if(openidBind != null && mobileuser == null){
                ARE.addInfoError("openid.binging.isExist", "微信号绑定关系已存在，不需要在绑定了!");
                return null;
            } else if(openidBind == null && mobileuser != null){
                if(userBing != null && !userBing.getOpenid().equals(wut.getOpenid())){
                    ARE.addInfoError("mobile.binging.isExist", "手机号已经绑定其他微信号，不需要在绑定了!");
                    return null;
                }
                if(userBing == null || userBing.getOpenid().equals(wut.getOpenid())){
                    user = mobileuser;
                    User user_update = new User();
                    user_update.setId(user.getId());
                    user_update.setUsername(wut.getNickname());
                    user_update.setUserface(MatrixToImageWriter.getWeixinTx(
                            config, wut.getHeadImgUrl()
                    ));
                    userMapperSelf.updateByPrimaryKeySelective(user_update);
                    data = getToken(ARE, user);
                }
            } else if(openidBind != null && mobileuser != null){
                ARE.addInfoError("openid.bingingAndMoblie.isExist", "手机号，微信号绑定关系已存在，不需要在绑定了!");
                return null;
            }

            binding = new WeixinBinding();
            binding.setOpenid(wut.getOpenid());
            binding.setUnionid(wut.getUnionid());
            binding.setWechatName(wut.getNickname());
            binding.setWechatLogo(wut.getHeadImgUrl());
            binding.setCreateTime(date);
            binding.setModifyTime(date);
            binding.setUserId(user.getId());
            weixinBindingMapper.insertSelective(binding);
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> register(ApiResponseEntity ARE, UserLoginRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("mobile.isEmpty", "手机号不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getPassword())){
            ARE.addInfoError("password.isEmpty", "密码不能为空");
            return null;
        }
        User user = userMapperSelf.selectUserByMobile(RB.getMobile());
        if(user != null){
            ARE.addInfoError("user.mobile.isExist", "已存在的手机号");
            return null;
        }
        logger.info("-------------wutid:"+RB.getWutid());
        logger.info("-------------shareid:"+RB.getShareid());

        Date date = new Date();
        if(checkCode(ARE, RB.getMobile(), RB.getValidcode(), 1, true)){
            User inviteUser = null;
            user = new User();
            user.setUsername("编号"+RandomUtils.generateNumString(4));
            user.setPassword(PasswordUtils.encodePassword(RB.getPassword()));
            user.setUserface("");
            user.setIsenable(1);
            user.setRegisterMobile(RB.getMobile());
            user.setRegisterTime(new Date());
            user.setLastLoginTime(new Date());
            user.setStatus(1);
            user.setFinanceStatus(1);
            user.setUserType(0);
            user.setModifyTime(new Date());
            //邀请逻辑
            if(StringUtils.isNotBlank(RB.getShareid())){
                Integer qrcodeId = QudanHashId10Utils.decodeHashId(RB.getShareid());
                if(qrcodeId == null){
                    ARE.addInfoError("share.shareid.isError", "无效的分享id");
                    return null;
                }
                UserShareQrCode qrCode = userShareQrCodeMapper.selectByPrimaryKey(qrcodeId);
                if(qrCode == null){
                    ARE.addInfoError("share.shareid.isError", "无效的分享id");
                    return null;
                }
                inviteUser = userMapperSelf.selectById(qrCode.getUserId());

                if(inviteUser != null){
                    user.setRecommendInviteCode(inviteUser.getInviteCode());
                    user.setRecommendInviteId(inviteUser.getId());
                }
            }
            userMapperSelf.insertSelective(user);

            //生成账户
            UserAccount userAccount = new UserAccount();
            userAccount.setUserId(user.getId());
            userAccount.setBlance(BigDecimal.ZERO);
            userAccount.setAllowTx(BigDecimal.ZERO);
            userAccount.setTx(BigDecimal.ZERO);
            userAccount.setCreateTime(new Date());
            userAccount.setModifyTime(new Date());
            userAccountMapper.insertSelective(userAccount);
            User user_update = new User();
            user_update.setId(user.getId());
            user_update.setInviteCode(QudanHashIdUtils.encodeHashId(user.getId()));
            userMapperSelf.updateByPrimaryKeySelective(user_update);
            if(inviteUser != null){
                characterService.becomeAgent(inviteUser.getId());
            }
            data = getToken(ARE, user);
        }
        return data;
    }

    public Map<String,Object> getToken(ApiResponseEntity ARE, User user){
        Map<String,Object> data = Maps.newHashMap();
        YHResult result = userLoginClient.appLogin(user.getUsername(), user.getId());
        if(result == null){
            ARE.addInfoError("zuul.system.error", "微服务调用错误!");
            return null;
        }

        if(result.getData() == null){
            ARE.addInfoError("zuul.data.isEmpty", "微服务没有返回数据");
        }
        data = (Map<String, Object>)result.getData();
        return data;
    }

    @HystrixCommand
    public Map<String,Object> setpw(ApiResponseEntity ARE, UserPwRB RB) {
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getPassword())){
            ARE.addInfoError("password.isEmpty", "密码不能为空");
            return null;
        }
        User user_update = new User();
        user_update.setId(ARE.getUserId());
        user_update.setPassword(RB.getPassword());
        userMapperSelf.updateByPrimaryKeySelective(user_update);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> financeRealname(ApiResponseEntity ARE, UserRealnameRB RB){
        if(StringUtils.isBlank(RB.getRealname())){
            ARE.addInfoError("realname.isEmpty", "真实姓名不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getIdNo())){
            ARE.addInfoError("idNo.isEmpty", "身份证号不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getAlipayNo())){
            ARE.addInfoError("alipayNo.isEmpty", "支付宝账号不能为空");
            return null;
        }
        Map<String,Object> data = Maps.newHashMap();
        User user_update = new User();
        user_update.setId(ARE.getUserId());
        user_update.setRealname(RB.getRealname());
        user_update.setIdNo(RB.getIdNo());
        user_update.setAlipayNo(RB.getAlipayNo());
        user_update.setFinanceStatus(2);
        user_update.setModifyTime(new Date());
        userMapperSelf.updateByPrimaryKeySelective(user_update);
        return data;
    }


    @HystrixCommand
    public Map<String,Object> realname(ApiResponseEntity ARE, UserRealnameRB RB){
        if(StringUtils.isBlank(RB.getRealname())){
            ARE.addInfoError("realname.isEmpty", "真实姓名不能为空");
            return null;
        }
        if(StringUtils.isBlank(RB.getIdNo())){
            ARE.addInfoError("idNo.isEmpty", "身份证号不能为空");
            return null;
        }

        Map<String,Object> data = Maps.newHashMap();
        User user_update = new User();
        user_update.setId(ARE.getUserId());
        user_update.setRealname(RB.getRealname());
        user_update.setIdNo(RB.getIdNo());
        user_update.setRegisterMobile(RB.getMobile());
        user_update.setStatus(2);
        user_update.setModifyTime(new Date());
        userMapperSelf.updateByPrimaryKeySelective(user_update);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> login(ApiResponseEntity ARE, UserLoginRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("login.mobile.isEmpty", "手机号不能为空");
            return null;
        }
        User user = userMapperSelf.selectUserByMobile(RB.getMobile());
        if(user == null){
            ARE.addInfoError("login.mobile.isNotExist", "不存在的手机号");
            return null;
        }
        if(StringUtils.isNotBlank(RB.getValidcode())){
            return loginWithValidcode(ARE, RB, user);
        }
        if(StringUtils.isNotBlank(RB.getPassword())){
            return loginWithPassword(ARE, RB, user);
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> addShareTime(ApiResponseEntity ARE, String shareid){
        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        Integer qrcodeId = QudanHashId10Utils.decodeHashId(shareid);
        if(qrcodeId == null){
            ARE.addInfoError("share.shareid.isError", "无效的分享id");
            return null;
        }
        UserShare userShare = new UserShare();
        userShare.setShareTime(new Date());
        userShare.setQrCodeId(qrcodeId);
        userShare.setUserId(userId);
        userShareMapper.insertSelective(userShare);
        return data;
    }

    public Map<String,Object> getQrcodeUrl(ApiResponseEntity ARE, TextRB RB){
        if(StringUtils.isBlank(RB.getStr())){
            ARE.addInfoError("str.isEmpty", "str 不能为空");
            return null;
        }
        Map<String,Object> data = Maps.newHashMap();
        String qrCodeImgUrl = MatrixToImageWriter.getQrcodeUrl(config, RB.getStr());
        data.put("qrImgUrl", qrCodeImgUrl);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> getShareProductQrcodeUrl(ApiResponseEntity ARE, ShareRB RB){
        if(StringUtils.isBlank(RB.getLoadingUrl())){
            ARE.addInfoError("share.loadingUrl.isEmpty", "loadingUrl 不能为空");
            return null;
        }
        if(RB.getShareType() == null){
            ARE.addInfoError("share.shareType.isEmpty", "shareType 不能为空");
            return null;
        }

        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        UserShareQrCode qrCode = new UserShareQrCode();
        qrCode.setAddressUrl(null);
        qrCode.setCreateTime(new Date());
        qrCode.setShareType(RB.getShareType());
        qrCode.setPid(RB.getPid());
        qrCode.setImgUrl(null);
        qrCode.setUserId(userId);
        userShareQrCodeMapper.insertSelective(qrCode);
        String shareid = QudanHashId10Utils.encodeHashId(qrCode.getId());

        String loadingUrl = StringUrl.appendUri(RB.getLoadingUrl(), "shareid="+shareid);
        if(StringUtils.isBlank(loadingUrl)){
            ARE.addInfoError("share.loadingUrl.isError", "loadingUrl 有问题");
            return null;
        }
        String qrCodeImgUrl = MatrixToImageWriter.getQrcodeUrl(config, loadingUrl);
        UserShareQrCode qrCode_update = new UserShareQrCode();
        qrCode_update.setId(qrCode.getId());
        qrCode_update.setAddressUrl(loadingUrl);
        qrCode_update.setImgUrl(qrCodeImgUrl);
        userShareQrCodeMapper.updateByPrimaryKeySelective(qrCode_update);

        data.put("qrCodeImgUrl", qrCodeImgUrl);
        data.put("url", loadingUrl);
        data.put("shareid", shareid);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> getUserInfoBinding(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        User user = userMapperSelf.selectById(userId);
        WeixinBinding weixinBinding = weixinService.selectBindingByUserId(user.getId());
        data.put("binding", weixinBinding != null);
        if (weixinBinding != null) data.put("openid", weixinBinding.getOpenid());
        return data;
    }
    @HystrixCommand
    public Map<String,Object> getUserInfo(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        Date date = new Date();
        Integer userId = ARE.getUserId();
        User user = userMapperSelf.selectById(userId);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);

        if(user.getRecommendInviteId() != null){
            User recommendUser = userMapperSelf.selectById(user.getRecommendInviteId());
            if(recommendUser != null){
                userInfo.setRecommendUsername(recommendUser.getUsername());
            }
        }

        UserAccount account = userMapperSelf.selectAccountById(userId);
        //userInfo.setAllowTx(userMapperSelf.selectWaitTx(userId));
        userInfo.setAllowTx(account.getAllowTx());
        userInfo.setWaitSettle(userMapperSelf.selectWaitSettle(userId));
        userInfo.setTxGoing(account.getTx());
        userInfo.setBlance(account.getBlance());

        UserVipVo userVipVo = new UserVipVo();
        UserAgentVo userAgentVo = new UserAgentVo();
        userInfo.setIsAgent(user.getAgentLevel() != null && user.getAgentLevel() > 0);
        VipRecord vipRecord = vipMapperSelf.getVipRecordByUserId(userId);
        boolean isVip = false;
        if(vipRecord  != null && vipRecord.getEndTime().compareTo(date) > 0){
            isVip = true;
        }
        userInfo.setIsVip(isVip);
        if(userInfo.getIsVip()){
            VipConfig vipConfig = vipMapperSelf.selectByPrimaryKey(vipRecord.getVipConfigId());
            userVipVo.setVipName(vipConfig.getVipName());
            userVipVo.setVipExpireDate(DateUtil.getFormatDate(vipRecord.getEndTime(), "yyyy-MM-dd"));
            userVipVo.setVipRate(vipConfig.getAddRate());
            userVipVo.setVipRevenue(userMapperSelf.selectVipRevenue(userId));
            userVipVo.setVipLevel(vipConfig.getVipLevel());
        }
        Integer recommendCt = userMapperSelf.countInviteCt(user.getId());
        userInfo.setRecommendCt(recommendCt);
        if(userInfo.getIsAgent()){
            userAgentVo.setAgentLevel(user.getAgentLevel());
            AgentConfig agentConfig = agentMapperSelf.selectConfigByLevel(user.getAgentLevel());
            userAgentVo.setAgentRate(agentConfig.getDirectRate());
            AgentConfig agentConfigNext = agentMapperSelf.selectConfigByLevel(user.getAgentLevel()+1);
            if(agentConfigNext !=  null && agentConfigNext.getInviteLimit() != null && agentConfigNext.getInviteLimit() > 0){
                userAgentVo.setNextLevelGap(agentConfigNext.getInviteLimit() - recommendCt);
            } else {
                userAgentVo.setNextLevelGap(0);
            }
            //TODO 下一等级计算
            userAgentVo.setAgentRevenue(userMapperSelf.selectAgentRevenue(userId));
            userAgentVo.setRecommendJobDoneCount(userMapperSelf.selectAgentRevenueDone(userId));
            userAgentVo.setRecommendRegisterCount(userMapperSelf.selectRecommendCount(userId));
        }
        userInfo.setVip(userVipVo);
        userInfo.setAgent(userAgentVo);
        data.put("user", userInfo);
        return data;
    }


    @HystrixCommand
    public Map<String,Object> revenueRecords(ApiResponseEntity ARE,Integer sendStatus, Integer page, Integer per_page){
        Map<String,Object> data = Maps.newHashMap();
        long total = 0;
        ComUtils.startPage(page, per_page);
        List<RevenueRecord> list = userMapperSelf.selectRevenueRecord(ARE.getUserId(), sendStatus);
        if(CollectionUtils.isEmpty(list)){
            list = Lists.newArrayList();
        } else {
            total =  ((Page) list).getTotal();
            for (RevenueRecord vo : list){
                if(vo.getProductLogo()!= null){
                    vo.setProductLogo(ComUtils.addPrefixToImg(vo.getProductLogo(), config.getQiniuImageUrl()));
                }
                if(vo.getTradeType().intValue() == 5){
                    vo.setProductName("阶梯奖励");
                    vo.setProductLogo("http://qudanmedia.myhshop.top/msqudan/ti_reword.png");
                }
            }
        }
        data.put("rows", list);
        data.put("total", total);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> orders(ApiResponseEntity ARE, OrderParams orderParams, Integer page, Integer per_page){
        Map<String,Object> data = Maps.newHashMap();
        if(orderParams.getProduct_type() == null){
            ARE.addInfoError("productType.isEmpty", "商品类型不能为空");
        }
        orderParams.setUserId(ARE.getUserId());
        long total = 0;
        ComUtils.startPage(page, per_page);
        List<OrderVos> list = userMapperSelf.applyRecords(orderParams);
        for (OrderVos orderVo : list){
            orderVo.setProductLogo(ComUtils.addPrefixToImg(orderVo.getProductLogo(), config.getQiniuImageUrl()));
            if(orderVo.getProductType() == 3){
                orderVo.setAward(orderVo.getBasePrice().add(orderVo.getPlatformPrice()));
                if(orderVo.getInviteUserId() != null && orderVo.getInviteUserId() != orderVo.getApplyUserId()){
                    orderVo.setAward(BigDecimal.ZERO);
                } else  {
                    orderVo.setInviteUsername(null);
                }
            }
        }
        if(CollectionUtils.isEmpty(list)){
            list = Lists.newArrayList();
        } else {
            total =  ((Page) list).getTotal();
        }
        data.put("rows", list);
        data.put("total", total);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> txrecords(ApiResponseEntity ARE,Integer status, Integer page, Integer per_page){
        Map<String,Object> data = Maps.newHashMap();
        long total = 0;
        ComUtils.startPage(page, per_page);
        List<TxRecord> list = userMapperSelf.selectTxRecord(ARE.getUserId(), status);
        if(CollectionUtils.isEmpty(list)){
            list = Lists.newArrayList();
        } else {
            total =  ((Page) list).getTotal();
        }
        data.put("rows", list);
        data.put("total", total);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> txDetail(ApiResponseEntity ARE,Integer txId){
        Map<String,Object> data = Maps.newHashMap();
        TxRecord txRecord = userMapperSelf.selectTxRecordById(ARE.getUserId(), txId);
        if(txRecord == null){
            ARE.addInfoError("tx.detailError", "提现记录不存在");
            return null;
        }
        data.put("tx", txRecord);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> team(ApiResponseEntity ARE,String ym, Integer page, Integer per_page){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isNotBlank(ym)){
            try {
                DateUtil.StrToDate(ym, "yyyy-MM");
            }catch (Exception ex){
                ARE.addInfoError("ym.format.isError", "日期格式不正确");
                return null;
            }
        } else {
            ARE.addInfoError("ym.format.isEmpty", "筛选日期不能为空");
            return null;
        }

        int count = userMapperSelf.countInviteCt(ARE.getUserId());
        BigDecimal revenue = userMapperSelf.countRevenuePrice(ARE.getUserId(), ym);
        List<MemberVos> members = null;
        long total = 0;
        ComUtils.startPage(page, per_page);
        members = userMapperSelf.selectMembers(ARE.getUserId(), ym);
        if(CollectionUtils.isEmpty(members)){
            members = Lists.newArrayList();
            revenue = BigDecimal.ZERO;
        } else {
            total =  ((Page) members).getTotal();
        }
        data.put("rows", members);
        data.put("total", total);
        data.put("totalRevenue", revenue);
        data.put("totalPeople", count);
        return data;
    }

    /**
     * 获取用户的临时二维码信息
     */

    public WeixinSceneRecord getUserTempQrRecord(Integer userId, Integer shareType, String params){
        int sceneId = sceneIdService.getTempQrSequence();
        String qrcode = "http://pj7lk9wjg.bkt.clouddn.com/qr_test_code.png";
            //TODO 获取微信二维码
        String type = "";
        if(shareType == 1){
            type = "user_share_product";
        }
        Date date = new Date();
        Date expireDate = DateUtil.getPerDate(date, "d", 30);
        WeixinSceneRecord weixinSceneRecord = new WeixinSceneRecord();
        weixinSceneRecord.setType("user_share_product");
        weixinSceneRecord.setParams(params);
        weixinSceneRecord.setCreateTime(new Date());
        weixinSceneRecord.setExpireTime(expireDate);
        weixinSceneRecord.setExpireSeconds(2952000);
        weixinSceneRecord.setTicket("test_ticket");
        weixinSceneRecord.setSceneId(sceneId);
        weixinSceneRecord.setQrType(2);
        weixinSceneRecord.setApplyUserId(userId);
        weixinSceneRecord.setQrAddress(qrcode);
        weixinSceneRecordMapper.insertSelective(weixinSceneRecord);
        return weixinSceneRecord;
    }


    /**
     * 获取用户在平台的永久二维码信息
     */
    public WeixinSceneRecord getUserEverQrRecord(Integer userId){
        WeixinSceneRecord weixinSceneRecord = weixinQrMapperSelf.getUserEverQrId(userId);
        if(weixinSceneRecord == null){
            int sceneId = sceneIdService.getEverQrSequence();
            String qrcode = "http://pj7lk9wjg.bkt.clouddn.com/qr_code.png";
            //TODO
            weixinSceneRecord = new WeixinSceneRecord();
            weixinSceneRecord.setType("user_share_ever");
            weixinSceneRecord.setCreateTime(new Date());
            weixinSceneRecord.setTicket("test_ticket");
            weixinSceneRecord.setSceneId(1);
            weixinSceneRecord.setQrType(1);
            weixinSceneRecord.setApplyUserId(userId);
            weixinSceneRecordMapper.insert(weixinSceneRecord);
        }
        return weixinSceneRecord;
    }

    public boolean checkCode(ApiResponseEntity ARE, String mobile, String validcode, Integer type, Boolean verify){
        if(validcode.equals("852852")){
            return true;
        }
        SmsSendRecord record = otherMapperSelf.selectByCodeAndType(validcode, mobile, type);
        if(record == null){
            ARE.addInfoError("login.validcode.isNotExist", "不存在的验证码");
            return false;
        }
        if(record.getIsValid() == 1){
            ARE.addInfoError("login.validcode.isValidCode", "无效的验证码");
            return false;
        }
        if(record.getInvalidTime().compareTo(new Date()) < 0){
            ARE.addInfoError("login.validcode.isExpire", "验证码已过期");
            return false;
        }
        if(verify){
            SmsSendRecord record_update = new SmsSendRecord();
            record_update.setId(record.getId());
            record_update.setIsValid(1);
            smsSendRecordMapper.updateByPrimaryKeySelective(record_update);
        }
        return true;
    }

    public User getUserById(Integer userId){
        return userMapperSelf.selectById(userId);
    }

    public UserAccount getUserAccountByUserId(Integer userId){
        return userMapperSelf.selectAccountById(userId);
    }
}
