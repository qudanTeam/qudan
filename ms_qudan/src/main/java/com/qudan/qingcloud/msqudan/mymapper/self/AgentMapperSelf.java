package com.qudan.qingcloud.msqudan.mymapper.self;

import com.qudan.qingcloud.msqudan.entity.AgentConfig;
import com.qudan.qingcloud.msqudan.mymapper.AgentConfigMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AgentMapperSelf extends AgentConfigMapper {
    @Select({
        "SELECT * FROM agent_config WHERE level = #{level}"
    })
    AgentConfig selectConfigByLevel(@Param("level")int level);


    @Select({
        "SELECT * FROM agent_config ORDER BY level ASC"
    })
    List<AgentConfig> selectConfigs();

    @Select({
        "SELECT ac.* FROM user u",
        "LEFT JOIN agent a ON u.id = a.user_id",
        "LEFT JOIN agent_config  ac ON ac.level = a.level",
        "WHERE user_id = #{userId}"
    })
    AgentConfig selectConfigByUserId(@Param("userId")Integer userId);
}
