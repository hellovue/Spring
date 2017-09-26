//*****************************************************************************
// The file is automatically generated by the program, do not manually modify. 
//*****************************************************************************

package org.github.spring.base.mapper;

import java.util.List;
import javax.annotation.Nullable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.github.spring.annotation.QueryInterface;
import org.github.spring.base.entity.BlogEntity;
import org.github.spring.base.example.BlogExample;
import org.github.spring.base.key.BlogKey;
import org.github.spring.footstone.MyBatisMapper;

@QueryInterface
public interface BlogMapper extends MyBatisMapper {
  long countByExample(BlogExample example);

  int deleteByExample(BlogExample example);

  @Delete({
    "delete from sampledb.t_blog",
    "where tid = #{tid,jdbcType=VARCHAR}"
  })
  int deleteByPrimaryKey(BlogKey key);

  @Insert({
    "insert into sampledb.t_blog (tid, user_id, ",
    "title, author, `type`, ",
    "loadURL, `label`, ",
    "decoration, create_time, ",
    "alter_time, `state`)",
    "values (#{tid,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
    "#{title,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, #{type,jdbcType=CHAR}, ",
    "#{loadurl,jdbcType=VARCHAR}, #{label,jdbcType=VARCHAR}, ",
    "#{decoration,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
    "#{alterTime,jdbcType=TIMESTAMP}, #{state,jdbcType=CHAR})"
  })
  int insert(BlogEntity record);

  int insertSelective(BlogEntity record);

  List<BlogEntity> selectByExample(BlogExample example);

  @Select({
    "select",
    "tid, user_id, title, author, `type`, loadURL, `label`, decoration, create_time, ",
    "alter_time, `state`",
    "from sampledb.t_blog",
    "where tid = #{tid,jdbcType=VARCHAR}"
  })
  @ResultMap("org.github.spring.base.mapper.BlogMapper.BaseResultMap")
  BlogEntity selectByPrimaryKey(BlogKey key);

  int updateByExampleSelective(@Param("record") BlogEntity record, @Param("example") BlogExample example);

  int updateByExample(@Param("record") BlogEntity record, @Param("example") BlogExample example);

  int updateByPrimaryKeySelective(BlogEntity record);

  @Update({
    "update sampledb.t_blog",
    "set user_id = #{userId,jdbcType=VARCHAR},",
      "title = #{title,jdbcType=VARCHAR},",
      "author = #{author,jdbcType=VARCHAR},",
      "`type` = #{type,jdbcType=CHAR},",
      "loadURL = #{loadurl,jdbcType=VARCHAR},",
      "`label` = #{label,jdbcType=VARCHAR},",
      "decoration = #{decoration,jdbcType=VARCHAR},",
      "create_time = #{createTime,jdbcType=TIMESTAMP},",
      "alter_time = #{alterTime,jdbcType=TIMESTAMP},",
      "`state` = #{state,jdbcType=CHAR}",
    "where tid = #{tid,jdbcType=VARCHAR}"
  })
  int updateByPrimaryKey(BlogEntity record);

  int batchInsert(List<BlogEntity> list);

  int batchUpdate(List<BlogEntity> list);

  int upsert(@Param("record") BlogEntity record, @Param("array") String[] array);

  int upsertSelective(@Param("record") BlogEntity record, @Param("array") String[] array);

  @Nullable
  BlogEntity selectByUniqueKey(BlogEntity record) throws TooManyResultsException;
}