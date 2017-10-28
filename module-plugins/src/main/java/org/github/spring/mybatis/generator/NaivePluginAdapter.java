package org.github.spring.mybatis.generator;

import java.util.List;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.google.common.base.Joiner;

public class NaivePluginAdapter extends PluginAdapter {
  static final Joiner JOINER = Joiner.on("").skipNulls();

  static final FullyQualifiedJavaType IMPORT_NULLABLE = new FullyQualifiedJavaType("javax.annotation.Nullable");

  static final String ANNOTATION_NULLABLE = "@Nullable";

  @Override
  public boolean validate(List<String> warnings) {
    return warnings.isEmpty();
  }

  InnerClass getGeneratedCriteria(TopLevelClass topLevelClass) {
    InnerClass generatedCriteria = null;
    for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
      if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) {
        generatedCriteria = innerClass;
        break;
      }
    }
    return generatedCriteria;
  }

  InnerClass getCriteria(TopLevelClass topLevelClass) {
    InnerClass criteria = null;
    for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
      if ("Criteria".equals(innerClass.getType().getShortName())) {
        criteria = innerClass;
        break;
      }
    }
    return criteria;
  }
}
