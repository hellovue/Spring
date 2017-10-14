package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

/**
 * EqualsHashCodePlugin.
 *
 * @author JYD_XL
 */
@Slf4j
public class EqualsHashCodePlugin extends NaivePluginAdapter {
  /** EqualsAndHashCode ---- annotation . */
  private static final String EQUALS_AND_HASHCODE_ANNOTATION_TRUE = "@EqualsAndHashCode(callSuper = true)";
  
  /** EqualsAndHashCode ---- annotation . */
  private static final String EQUALS_AND_HASHCODE_ANNOTATION_FALSE = "@EqualsAndHashCode";
  
  /** EqualsAndHashCode ---- import . */
  private static final FullyQualifiedJavaType EQUALS_AND_HASHCODE_IMPORT = new FullyQualifiedJavaType("lombok.EqualsAndHashCode");
  
  private static final FullyQualifiedJavaType TYPE_OBJECT = new FullyQualifiedJavaType("java.lang.Object");
  
  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addAnnotation(topLevelClass);
    return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
  }
  
  @Override
  public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addAnnotation(topLevelClass);
    return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
  }
  
  @Override
  public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addAnnotation(topLevelClass);
    return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
  }
  
  private void addAnnotation(TopLevelClass topLevelClass) {
    if (isNull(topLevelClass.getSuperClass()) || TYPE_OBJECT.equals(topLevelClass.getSuperClass())) {
      topLevelClass.addAnnotation(EQUALS_AND_HASHCODE_ANNOTATION_FALSE);
    } else {
      topLevelClass.addAnnotation(EQUALS_AND_HASHCODE_ANNOTATION_TRUE);
    }
    topLevelClass.addImportedType(EQUALS_AND_HASHCODE_IMPORT);
  }
}
