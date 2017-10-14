package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * QueryMapperPlugin.
 *
 * @author JYD_XL
 */
public class QueryMapperPlugin extends NaivePluginAdapter {
  @Override
  public boolean clientGenerated(Interface mapperInterface, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    mapperInterface.addImportedType(new FullyQualifiedJavaType("org.github.spring.annotation.QueryMapper"));
    mapperInterface.addAnnotation("@QueryMapper");
    return super.clientGenerated(mapperInterface, topLevelClass, introspectedTable);
  }
}