package com.qudan.qingcloud.msqudan.service.Impl;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qudan.qingcloud.msqudan.cloudClients.UserLoginClient;
import com.qudan.qingcloud.msqudan.entity.*;
import com.qudan.qingcloud.msqudan.mymapper.*;
import com.qudan.qingcloud.msqudan.mymapper.self.*;
import com.qudan.qingcloud.msqudan.util.*;
import com.qudan.qingcloud.msqudan.util.params.OrderParams;
import com.qudan.qingcloud.msqudan.util.requestBody.UserLoginRB;
import com.qudan.qingcloud.msqudan.util.responses.*;
import org.apache.commons.lang.StringUtils;
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
        if(checkCode(ARE, RB.getMobile(), RB.getValidcode(), 1, true)){
            User user = userMapperSelf.selectUserByMobile(RB.getMobile());
            if(user != null){
                ARE.addInfoError("user.mobile.isExist", "已存在的手机号");
                return null;
            }
            user = new User();
            user.setUsername("编号"+RandomUtils.generateNumString(4));
            user.setPassword(PasswordUtils.encodePassword(RB.getPassword()));
            user.setUserface("");
            user.setIsenable(1);
            user.setRegisterMobile(RB.getMobile());
            user.setRegisterTime(new Date());
            user.setLastLoginTime(new Date());
            user.setStatus(0);
            user.setUserType(0);
            user.setInviteCode(RandomUtils.generateMixString(16));
            user.setModifyTime(new Date());
            //TODO 邀请逻辑
            userMapperSelf.insertSelective(user);

            //生成账户
            UserAccount userAccount = new UserAccount();
            userAccount.setUserId(user.getId());
            userAccount.setBlance(BigDecimal.ZERO);
            userAccount.setAllowTx(BigDecimal.ZERO);
            userAccount.setTx(BigDecimal.ZERO);
            userAccount.setCreateTime(new Date());
            userAccount.setModifyTime(new Date());
            data = getToken(ARE, user);
            userAccountMapper.insertSelective(userAccount);
        }
        return data;
    }

    private Map<String,Object> getToken(ApiResponseEntity ARE, User user){
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
    public Map<String,Object> login(ApiResponseEntity ARE, UserLoginRB RB){
        Map<String,Object> data = Maps.newHashMap();
        if(StringUtils.isBlank(RB.getMobile())){
            ARE.addInfoError("login.mobile.isEmpty", "手机号不能为空");
            return null;
        }
        User user = userMapperSelf.selectUserByMobile(RB.getMobile());
        if(StringUtils.isNotBlank(RB.getValidcode())){
            return loginWithValidcode(ARE, RB, user);
        }
        if(StringUtils.isNotBlank(RB.getPassword())){
            return loginWithPassword(ARE, RB, user);
        }
        return data;
    }

    @HystrixCommand
    public Map<String,Object> addShareTime(ApiResponseEntity ARE, String ticket){
        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        WeixinSceneRecord record = weixinQrMapperSelf.getQrRecordByTicket(ticket, userId);
        if(record == null){
            ARE.addInfoError("ticket.isNotExist", "不存在的ticket");
            return null;
        }
        UserShare userShare = new UserShare();
        userShare.setShareTime(new Date());
        userShare.setWeixinSceneId(record.getSceneId());
        userShare.setUserId(userId);
        userShareMapper.insertSelective(userShare);
        return data;
    }

    @HystrixCommand
    public Map<String,Object> getShareProductQrcode(ApiResponseEntity ARE,Integer type, Integer productId){
        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        String params = "pid="+productId;
        WeixinSceneRecord record = getUserTempQrRecord(userId, type, params);
        data.put("ticket", record.getTicket());
        data.put("qrCode", record.getQrAddress());
        return data;
    }

    @HystrixCommand
    public Map<String,Object> getUserInfo(ApiResponseEntity ARE){
        Map<String,Object> data = Maps.newHashMap();
        Integer userId = ARE.getUserId();
        User user = userMapperSelf.selectById(userId);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);

        UserAccount account = userMapperSelf.selectAccountById(userId);
        userInfo.setAllowTx(userMapperSelf.selectWaitTx(userId));
        userInfo.setWaitSettle(userMapperSelf.selectWaitSettle(userId));
        userInfo.setBlance(account.getBlance());

        UserVipVo userVipVo = new UserVipVo();
        UserAgentVo userAgentVo = new UserAgentVo();
        userInfo.setIsAgent(user.getAgentLevel() != null && user.getAgentLevel() > 0);
        userInfo.setIsVip(StringUtils.isNotBlank(user.getVipName()));
        if(userInfo.getIsVip()){
            VipRecord record = vipMapperSelf.selectVipById(userId);
            VipConfig vipConfig = vipMapperSelf.selectByPrimaryKey(record.getVipConfigId());
            userVipVo.setVipName(vipConfig.getVipName());
            userVipVo.setVipExpireDate(DateUtil.getFormatDate(record.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            userVipVo.setVipRate(vipConfig.getAddRate());
            userVipVo.setVipRevenue(userMapperSelf.selectVipRevenue(userId));
        }
        if(userInfo.getIsAgent()){
            userAgentVo.setAgentLevel(user.getAgentLevel());
            AgentConfig agentConfig = agentMapperSelf.selectConfigByLevel(user.getAgentLevel());
            userAgentVo.setAgentRate(agentConfig.getDirectRate());

            //TODO 下一等级计算
            userAgentVo.setNextLevelGap(20);
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
        }
        data.put("rows", list);
        data.put("total", total);
        return data;
    }



    @HystrixCommand
    public Map<String,Object> orders(ApiResponseEntity ARE, OrderParams orderParams, Integer page, Integer per_page){
        Map<String,Object> data = Maps.newHashMap();
        if(orderParams.getProductType() == null){
            ARE.addInfoError("productType.isEmpty", "商品类型不能为空");
        }
        orderParams.setUserId(ARE.getUserId());
        long total = 0;
        ComUtils.startPage(page, per_page);
        List<OrderVos> list = userMapperSelf.applyRecords(orderParams);
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

        int count = userMapperSelf.countRevenuePeople(ARE.getUserId(), ym);
        BigDecimal revenue = userMapperSelf.countRevenuePrice(ARE.getUserId(), ym);
        List<MemberVos> members = null;
        long total = 0;
        if(count > 0){
            ComUtils.startPage(page, per_page);
            members = userMapperSelf.selectMember(ARE.getUserId(), ym);
            if(CollectionUtils.isEmpty(members)){
                members = Lists.newArrayList();
            } else {
                total =  ((Page) members).getTotal();
            }
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
            //TODO
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
}
