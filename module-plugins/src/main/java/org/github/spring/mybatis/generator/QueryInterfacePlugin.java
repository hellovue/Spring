package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * QueryInterfacePlugin.
 *
 * @author JYD_XL
 */
public class QueryInterfacePlugin extends NaivePluginAdapter {
  private static final String QUERY_INTERFACE_ANNOTATION = "@QueryInterface";
  
  private static final FullyQualifiedJavaType QUERY_INTERFACE_ANNOTATION_TYPE = new FullyQualifiedJavaType("org.github.spring.annotation.QueryInterface");
  
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    interfaze.addAnnotation(QUERY_INTERFACE_ANNOTATION);
    interfaze.addImportedType(QUERY_INTERFACE_ANNOTATION_TYPE);
    return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
  }
}