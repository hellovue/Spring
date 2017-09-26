package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * SqlMapperXMLOverridePlugin.
 *
 * @author JYD_XL
 */
@Slf4j
public class SqlMapperXMLOverridePlugin extends NaivePluginAdapter {
  @Override
  public boolean sqlMapGenerated(GeneratedXmlFile generatedXmlFile, IntrospectedTable introspectedTable) {
    try {
      val isMergeable = generatedXmlFile.getClass().getDeclaredField("isMergeable");
      isMergeable.setAccessible(true);
      isMergeable.setBoolean(generatedXmlFile, false);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
      log.error("SqlMapperXMLOverriding-Exception ==> " + e.getMessage(), e);
    }
    return super.sqlMapGenerated(generatedXmlFile, introspectedTable);
  }
}
