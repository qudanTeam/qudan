package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.SmsSendRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OtherMapperSelf {


    @Select({
        "SELECT * FROM sms_send_record WHERE code = #{code} AND send_type = #{type} ORDER BY id DESC"
    })
    SmsSendRecord selectByCodeAndType(@Param("code") String code, @Param("mobile")String mobile, @Param("type")Integer type);
}
