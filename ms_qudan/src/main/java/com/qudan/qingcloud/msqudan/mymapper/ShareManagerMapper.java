package com.qudan.qingcloud.msqudan.mymapper;

import com.qudan.qingcloud.msqudan.entity.ShareManager;
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

public interface ShareManagerMapper {
    @Delete({
        "delete from share_manager",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into share_manager (product_id, is_show, ",
        "share_img, modify_tiime, ",
        "create_time, sort_val, ",
        "content)",
        "values (#{productId,jdbcType=INTEGER}, #{isShow,jdbcType=INTEGER}, ",
        "#{shareImg,jdbcType=VARCHAR}, #{modifyTiime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{sortVal,jdbcType=INTEGER}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ShareManager record);

    @InsertProvider(type=ShareManagerSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ShareManager record);

    @Select({
        "select",
        "id, product_id, is_show, share_img, modify_tiime, create_time, sort_val, content",
        "from share_manager",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_show", property="isShow", jdbcType=JdbcType.INTEGER),
        @Result(column="share_img", property="shareImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_tiime", property="modifyTiime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sort_val", property="sortVal", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    ShareManager selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ShareManagerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ShareManager record);

    @Update({
        "update share_manager",
        "set product_id = #{productId,jdbcType=INTEGER},",
          "is_show = #{isShow,jdbcType=INTEGER},",
          "share_img = #{shareImg,jdbcType=VARCHAR},",
          "modify_tiime = #{modifyTiime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "sort_val = #{sortVal,jdbcType=INTEGER},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ShareManager record);

    @Update({
        "update share_manager",
        "set product_id = #{productId,jdbcType=INTEGER},",
          "is_show = #{isShow,jdbcType=INTEGER},",
          "share_img = #{shareImg,jdbcType=VARCHAR},",
          "modify_tiime = #{modifyTiime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "sort_val = #{sortVal,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ShareManager record);
}