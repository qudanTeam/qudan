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

    @Select({
        "SELECT SUM(price) FROM trade_type WHERE send_status = 1 AND status = 2 AND user_id = #{userId}"
    })
    BigDecimal selectWaitTx(@Param("userId") Integer userId);

    @Select({
        "SELECT SUM(price) FROM trade_type WHERE send_status = 1 AND status = 1 AND user_id = #{userId}"
    })
    BigDecimal selectWaitSettle(@Param("userId") Integer userId);

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
                "p.product_name productName,",
                "p.log productLogo,",
                "apply.mobile mobile,",
                "apply.apply_id_code orderNo,",
                "apply.name applyName",
            "FROM apply",
            "LEFT JOIN product on p.id = apply.product_id",
            "WHERE apply.user_id = #{obj.userId}",
            "<if test=\"obj.productStatus != null\"> AND p.product_type = #{obj.productType} </if>",
            "<if test=\"obj.applyStatus != null\"> AND apply.status = #{applyStatus} </if>",
            "<if test=\"obj.officialStatus != null\"> AND apply.official_status = #{officialStatus} </if>",
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
            "reject_reason rejectReason",
        "FROM trade_type",
        "WHERE trade_type = 1 AND user_id = #{userId}",
        "WHERE user_id = #{userId}",
        "<if test=\"status != null\"> AND status = #{status} </if>",
        "ORDER BY create_time DESC",
        "</script>",
    })
    List<TxRecord> selectTxRecord(@Param("userId")Integer userId, @Param("status")Integer status);


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
        "WHERE (trade_type = 2 OR trade_type = 3) AND user_id = #{userId} AND status = 2",
        "AND user_id = #{userId}",
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
            "<if test=\"ym != null and ym is not null\"> AND date_format(audit_time, '%Y-%m-%d') = #{ym}; </if>",
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
            "AND status = 2 AND trade_type = 3",
            "<if test=\"ym != null and ym is not null\"> AND date_format(audit_time, '%Y-%m-%d') = #{ym}; </if>",
        "</script>",
    })
    BigDecimal countRevenuePrice(@Param("userId")Integer userId, @Param("ym")String ym);

    @Select({
    "<script>",
        "SELECT ",
            "relu.username name, ",
            "relu.mobile mobile,",
            "relu.register_time registerTime,",
            "ru.username recommendName,",
            "SUM(price)",
        "FROM trade_type t",
        "left join user relu on relu.id = t.relation_user_id",
        "LEFT JOIN user ru on ru.id = relu.recommend_invite_id", //推荐人一定A的团队成员，要不然不能产生分佣
        "LEFT JOIN apply on apply.id = t.apply_id",
        "WHERE ",
            "user_id = #{userId}",
            "AND status = 2 AND trade_type = 3",
            "<if test=\"ym != null and ym is not null\"> AND date_format(audit_time, '%Y-%m-%d') = #{ym}; </if>",
            "GROUP BY relu.username,relu.mobile,relu.register_time,ru.username ORDER BY relu.register_time DESC",
    "</script>",
    })
    List<MemberVos> selectMember(@Param("userId")Integer userId, @Param("ym")String ym);
}
