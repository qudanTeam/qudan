package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.AgentConfig;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AgentConfigMapper {
    @Delete({
        "delete from agent_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into agent_config (level, direct_rate, ",
        "related_rate, invite_limit, ",
        "task_done_limit, share_limit, ",
        "create_time, modify_time)",
        "values (#{level,jdbcType=INTEGER}, #{directRate,jdbcType=DECIMAL}, ",
        "#{relatedRate,jdbcType=DECIMAL}, #{inviteLimit,jdbcType=INTEGER}, ",
        "#{taskDoneLimit,jdbcType=INTEGER}, #{shareLimit,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(AgentConfig record);

    @InsertProvider(type=AgentConfigSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(AgentConfig record);

    @Select({
        "select",
        "id, level, direct_rate, related_rate, invite_limit, task_done_limit, share_limit, ",
        "create_time, modify_time",
        "from agent_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="direct_rate", property="directRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="related_rate", property="relatedRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="invite_limit", property="inviteLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="task_done_limit", property="taskDoneLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="share_limit", property="shareLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AgentConfig selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AgentConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AgentConfig record);

    @Update({
        "update agent_config",
        "set level = #{level,jdbcType=INTEGER},",
          "direct_rate = #{directRate,jdbcType=DECIMAL},",
          "related_rate = #{relatedRate,jdbcType=DECIMAL},",
          "invite_limit = #{inviteLimit,jdbcType=INTEGER},",
          "task_done_limit = #{taskDoneLimit,jdbcType=INTEGER},",
          "share_limit = #{shareLimit,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AgentConfig record);
}