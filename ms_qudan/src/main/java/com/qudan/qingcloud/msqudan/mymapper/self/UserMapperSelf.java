package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.entity.UserAccount;
import com.qudan.qingcloud.msqudan.entity.VipConfig;
import com.qudan.qingcloud.msqudan.entity.VipRecord;
import com.qudan.qingcloud.msqudan.mymapper.BannerMapper;
import com.qudan.qingcloud.msqudan.mymapper.UserMapper;
import com.qudan.qingcloud.msqudan.util.responses.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

public interface UserMapperSelf extends UserMapper {

    @Select({
        "SELECT * FROM user WHERE register_mobile = #{mobile}"
    })
    User selectUserByMobile(String mobile);

    @Select({
        "SELECT * FROM user WHERE id = #{id}"
    })
    UserInfo selectById(Integer id);

    @Select({
        "SELECT * FROM user_account WHERE user_Iid = #{userId}"
    })
    UserAccount selectAccountById(Integer userId);

    @Select({
        "SELECT SUM(price) FROM trade_type WHERE send_type = 1 AND status = 2 AND user_id = #{userId}"
    })
    BigDecimal selectWaitTx(@Param("userId") Integer userId);

    @Select({
        "SELECT SUM(price) FROM trade_type WHERE send_type = 1 AND status = 1 AND user_id = #{userId}"
    })
    BigDecimal selectWaitSettle(@Param("userId") Integer userId);

    @Select({
        "SELECT SUM(price) FROM trade_type WHERE trade_type = 3 AND send_type = 2 AND status = 2 AND user_id = #{userId}"
    })
    BigDecimal selectAgentRevenue(@Param("userId") Integer userId);

    @Select({
            "SELECT count(1) FROM trade_type WHERE trade_type = 3 AND send_type = 2 AND status = 2 AND user_id = #{userId}"
    })
    int selectAgentRevenueDone(@Param("userId") Integer userId);

    @Select({
        "SELECT count(1) FROM recommend_invite_id = #{userId}"
    })
    int selectRecommendCount(@Param("userId") Integer userId);

    @Select({
        "SELECT SUM(vip_price) FROM trade_type WHERE send_type = 2 AND status = 2 AND user_id = #{userId}"
    })
    BigDecimal selectVipRevenue(@Param("userId") Integer userId);
}
