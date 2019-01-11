package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.Message;
import com.qudan.qingcloud.msqudan.mymapper.MessageMapper;
import com.qudan.qingcloud.msqudan.util.responses.MessageVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MessageMapperSelf extends MessageMapper{

    @Select({
        "SELECT ",
            "id,",
            "msg_logo,",
            "msg_title,",
            "msg_content,",

            "msg_link,",
            "create_time,",
            "modify_time",
        "FROM message WHERE user_id = #{userId} AND is_user_delete = 0"
    })
    List<MessageVo> selectListByUserId(@Param("userId")Integer userId);

    @Select({
        "SELECT * FROM message WHERE id = #{id}"
    })
    MessageVo selectById(@Param("id")Integer id);

    @Update({
        "UPDATE message set is_user_delete = 1 WHERE user_id = #{userId}"
    })
    int updateMessageDelete(@Param("userId")Integer userId);
}
