package org.github.spring.mybatis.generator;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.plugins.SerializablePlugin;

import lombok.val;

public class NaiveSerializablePlugin extends SerializablePlugin {
  @Override
  public boolean validate(List<String> warnings) {
    return warnings.isEmpty();
  }
  
  @Override
  protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    val fullyQualifiedJavaType = new FullyQualifiedJavaType("java.io.Serializable");
    topLevelClass.addImportedType(fullyQualifiedJavaType);
    topLevelClass.addSuperInterface(fullyQualifiedJavaType);
    topLevelClass.addAnnotation("@SuppressWarnings(\"serial\")");
  }
}
