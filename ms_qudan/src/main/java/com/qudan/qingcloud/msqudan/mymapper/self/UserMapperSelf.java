package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.User;
import com.qudan.qingcloud.msqudan.entity.UserAccount;
import com.qudan.qingcloud.msqudan.entity.VipConfig;
import com.qudan.qingcloud.msqudan.entity.VipRecord;
import com.qudan.qingcloud.msqudan.mymapper.BannerMapper;
import com.qudan.qingcloud.msqudan.mymapper.UserMapper;
import com.qudan.qingcloud.msqudan.util.params.OrderParams;
import com.qudan.qingcloud.msqudan.util.responses.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
        "SELECT * FROM user_account WHERE user_id = #{userId}"
    })
    UserAccount selectAccountById(Integer userId);


    /**
     * 待提现金额
     * @param userId
     * @return
     */
    @Select({
        "SELECT SUM(price) FROM trade_type WHERE (trade_type = 2 OR trade_type = 3) AND send_status = 1 AND status = 2 AND user_id = #{userId}"
    })
    BigDecimal selectWaitTx(@Param("userId") Integer userId);


    /**
     * 待结算金额
     * @param userId
     * @return
     */
    @Select({
        "SELECT SUM(price) FROM trade_type WHERE (trade_type = 2 OR trade_type = 3) AND send_status = 1 AND status = 1 AND user_id = #{userId}"
    })
    BigDecimal selectWaitSettle(@Param("userId") Integer userId);


    /**
     * 提现中金额
     * @param userId
     * @return
     */
    @Select({
            "SELECT SUM(price) FROM trade_type WHERE trade_type = 1 AND send_status = 1 AND status = 1 AND user_id = #{userId}"
    })
    BigDecimal selectTxGoing(@Param("userId") Integer userId);

    @Select({
        "SELECT SUM(price) FROM trade_type WHERE trade_type = 3 AND send_status = 2 AND status = 2 AND user_id = #{userId}"
    })
    BigDecimal selectAgentRevenue(@Param("userId") Integer userId);


    /**
     * 团队任务完成人数
     * @param userId
     * @return
     */
    @Select({
            "SELECT count(1) FROM trade_type WHERE trade_type = 3 AND send_status = 2 AND status = 2 AND user_id = #{userId}"
    })
    int selectAgentRevenueDone(@Param("userId") Integer userId);

    /**
     * 推荐人数
     * @param userId
     * @return
     */
    @Select({
        "SELECT count(1) FROM user WHERE  recommend_invite_id = #{userId}"
    })
    int selectRecommendCount(@Param("userId") Integer userId);

    @Select({
        "SELECT SUM(vip_price) FROM trade_type WHERE send_status = 2 AND status = 2 AND vip_price IS NOT NULL AND user_id = #{userId}"
    })
    BigDecimal selectVipRevenue(@Param("userId") Integer userId);

    @Select({
        "<script>",
            "SELECT ",
                "p.id productId,",
                "apply.id applyId,",
                "p.product_type productType,",
                "p.product_name productName,",
                "p.logo productLogo,",
                "apply.mobile mobile,",
                "apply.apply_id_code orderNo,",
                "apply.name applyName,",
                "apply.status status,",
                "apply.official_status officialStatus,",
                "C.get_link getLink,",
                "C.need_verify_code needVerifyCode,",
                "C.need_mobile_verify_code needMobileVerifyCode,",
                "C.verify_code_link verifyCodeLink, ",
                "C.mobile_verify_code_link mobileVerifyCodeLink",
            "FROM apply",
            "LEFT JOIN product  p on p.id = apply.product_id",
            "LEFT JOIN product_category_relation PCR ON PCR.product_id = p.id",
            "LEFT JOIN category C ON C.id = PCR.category_id",
            "WHERE apply.user_id = #{obj.userId}",
            "<if test=\"obj.product_type != null\"> AND p.product_type = #{obj.product_type} </if>",
            "<if test=\"obj.apply_status != null\"> AND apply.status = #{obj.apply_status} </if>",
            "<if test=\"obj.official_status != null\"> AND apply.official_status = #{obj.official_status} </if>",
            "ORDER BY p.create_time DESC",
        "</script>",
    })
    List<OrderVos> applyRecords(@Param("obj")OrderParams params);

    

    @Select({
        "<script>",
        "SELECT",
            "create_time txDate,",
            "price revenue,",
            "status status,",
            "reject_reason rejectReason,",
            "tx_alipay_no alipayNo",
        "FROM trade_type",
        "WHERE trade_type = 1 AND user_id = #{userId}",
        "<if test=\"status != null\"> AND status = #{status} </if>",
        "ORDER BY create_time DESC",
        "</script>",
    })
    List<TxRecord> selectTxRecord(@Param("userId")Integer userId, @Param("status")Integer status);

    @Select({

        "SELECT",
        "create_time txDate,",
        "price revenue,",
        "status status,",
        "reject_reason rejectReason,",
        "tx_alipay_no alipayNo",
        "FROM trade_type",
        "WHERE trade_type = 1 AND user_id = #{userId} AND id = #{txId}",
    })
    TxRecord selectTxRecordById(@Param("userId")Integer userId, @Param("txId")Integer txId);


    @Select({
        "<script>",
        "SELECT",
            "t.audit_time auditTime,",
            "t.price revenue,",
            "t.send_status sendStatus,",
            "p.product_name productName,",
            "p.logo productLogo",
        "FROM trade_type t",
        "LEFT JOIN apply a on t.apply_id = a.id",
        "LEFT JOIN product p on p.id = a.product_id",
        "WHERE (trade_type = 2 OR trade_type = 3 OR trade_type = 5) AND t.user_id = #{userId} AND t.status = 2",
        "AND t.user_id = #{userId}",
            "<if test=\"sendStatus != null\"> AND send_status = #{sendStatus} </if>",
        "ORDER BY t.audit_time DESC",
        "</script>",
    })
    List<RevenueRecord> selectRevenueRecord(@Param("userId")Integer userId, @Param("sendStatus")Integer sendStatus);


    //date_format(now(), '%Y-%m-%d');
    @Select({
        "<script>",
            "SELECT ",
                "count(1)",
            "FROM trade_type",
            "WHERE ",
            "user_id = #{userId}",
            "AND status = 2 AND trade_type = 3",
            "<if test=\"ym != null and ym !=''\"> AND date_format(audit_time, '%Y-%m') = #{ym}</if>",
        "</script>",
    })
    int countRevenuePeople(@Param("userId")Integer userId, @Param("ym")String ym);

    @Select({
        "<script>",
            "SELECT ",
                "SUM(price)",
            "FROM trade_type",
            "WHERE ",
            "user_id = #{userId}",
            "AND trade_type = 3",
            "<if test=\"ym != null and ym != ''\"> AND date_format(audit_time, '%Y-%m') = #{ym}</if>",
        "</script>",
    })
    BigDecimal countRevenuePrice(@Param("userId")Integer userId, @Param("ym")String ym);

    @Select({
        "<script>",
            "SELECT",
                "relu.username name, ",
                "relu.register_mobile mobile,",
                "relu.register_time registerTime,",
                "ru.username recommendName,",
                "SUM(t.price)",
            "FROM user ru ",
            "left join user relu on ru.id = relu.recommend_invite_id",
            "left join trade_type t on relu.id = t.relation_user_id",//业绩人
            "LEFT JOIN apply on apply.id = t.apply_id",
            "WHERE ",
                "ru.id = #{userId}",
                "AND (",
                    "(t.id IS NOT NULL AND t.trade_type = 3) ",
                    "OR ",
                    "(t.id IS NULL) ",
                ")",
                "<if test=\"ym != null and ym != ''\"> AND date_format(t.audit_time, '%Y-%m') = #{ym} </if>",
                "GROUP BY ",
                    "relu.username,",
                    "relu.register_mobile,",
                    "relu.register_time,",
                    "ru.username ORDER ",
                "BY relu.register_time DESC",
        "</script>",
    })
    List<MemberVos> selectMembers(@Param("userId")Integer userId, @Param("ym")String ym);

    @Select({
    "<script>",
        "SELECT ",
            "relu.username name, ",
            "relu.register_mobile mobile,",
            "relu.register_time registerTime,",
            "ru.username recommendName,",
            "SUM(price)",
        "FROM trade_type t",
        "left join user relu on relu.id = t.relation_user_id",//业绩人
        "LEFT JOIN user ru on ru.id = relu.recommend_invite_id", //推荐人一定是A的团队成员，要不然不能产生分佣
        "LEFT JOIN apply on apply.id = t.apply_id",
        "WHERE ",
            "t.user_id = #{userId}",
            "AND trade_type = 3 OR trade",
            "<if test=\"ym != null and ym != ''\"> AND date_format(t.audit_time, '%Y-%m') = #{ym} </if>",
            "GROUP BY ",
                    "relu.username,",
                    "relu.mobile,",
                    "relu.register_time,",
                    "ru.username ORDER ",
            "BY relu.register_time DESC",
    "</script>",
    })
    List<MemberVos> selectMember(@Param("userId")Integer userId, @Param("ym")String ym);


    @Select({
        "SELECT count(1) FROM trade_type WHERE trade_type = 1 AND (status = 2 OR status =1) AND user_id = #{userId} AND audit_time < #{end} AND audit_time >= #{start}"
    })
    int countTxByTime(@Param("start")Date start , @Param("end")Date end, @Param("userId")Integer userId);

    @Select({
        "SELECT COUNT(1) FROM user_share WHERE user_id = #{userId}"
    })
    int countShareCt(Integer userId);

    @Select({
        "SELECT COUNT(1) FROM apply WHERE invite_code = #{inviteCode} AND status = 2 AND official_status =2"
    })
    int countTaskDoneCt(String inviteCode);

    @Select({
        "SELECT COUNT(1) FROM user WHERE recommend_invite_id = #{userId}"
    })
    int countInviteCt(Integer userId);
}
