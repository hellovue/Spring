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
import org.github.spring.base.entity.SourceEntity;
import org.github.spring.base.example.SourceExample;
import org.github.spring.base.key.SourceKey;
import org.github.spring.footstone.MyBatisMapper;

@QueryInterface
public interface SourceMapper extends MyBatisMapper {
  long countByExample(SourceExample example);

  int deleteByExample(SourceExample example);

  @Delete({
    "delete from sampledb.t_source",
    "where source_id = #{sourceId,jdbcType=VARCHAR}"
  })
  int deleteByPrimaryKey(SourceKey key);

  @Insert({
    "insert into sampledb.t_source (source_id, user_id, ",
    "title, `flag`, sdecotation, ",
    "source_url, create_time)",
    "values (#{sourceId,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
    "#{title,jdbcType=VARCHAR}, #{flag,jdbcType=VARCHAR}, #{sdecotation,jdbcType=VARCHAR}, ",
    "#{sourceUrl,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
  })
  int insert(SourceEntity record);

  int insertSelective(SourceEntity record);

  List<SourceEntity> selectByExample(SourceExample example);

  @Select({
    "select",
    "source_id, user_id, title, `flag`, sdecotation, source_url, create_time",
    "from sampledb.t_source",
    "where source_id = #{sourceId,jdbcType=VARCHAR}"
  })
  @ResultMap("org.github.spring.base.mapper.SourceMapper.BaseResultMap")
  SourceEntity selectByPrimaryKey(SourceKey key);

  int updateByExampleSelective(@Param("record") SourceEntity record, @Param("example") SourceExample example);

  int updateByExample(@Param("record") SourceEntity record, @Param("example") SourceExample example);

  int updateByPrimaryKeySelective(SourceEntity record);

  @Update({
    "update sampledb.t_source",
    "set user_id = #{userId,jdbcType=VARCHAR},",
      "title = #{title,jdbcType=VARCHAR},",
      "`flag` = #{flag,jdbcType=VARCHAR},",
      "sdecotation = #{sdecotation,jdbcType=VARCHAR},",
      "source_url = #{sourceUrl,jdbcType=VARCHAR},",
      "create_time = #{createTime,jdbcType=TIMESTAMP}",
    "where source_id = #{sourceId,jdbcType=VARCHAR}"
  })
  int updateByPrimaryKey(SourceEntity record);

  int batchInsert(List<SourceEntity> list);

  int batchUpdate(List<SourceEntity> list);

  int upsert(@Param("record") SourceEntity record, @Param("array") String[] array);

  int upsertSelective(@Param("record") SourceEntity record, @Param("array") String[] array);

  @Nullable
  SourceEntity selectByUniqueKey(SourceEntity record) throws TooManyResultsException;
}