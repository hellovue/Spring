package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class MethodParamNonNullOptimizationPlugin extends NaivePluginAdapter {
  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    InnerClass criteria = this.getCriteria(topLevelClass);
    InnerClass generatedCriteria = this.getGeneratedCriteria(topLevelClass);
    if (generatedCriteria == null) return true;
    if (criteria == null) return true;

    String methodBetween = "Between";
    String methodNull = "Null";
    String methodIn = "In";
    String methodCriteria = "Criteria";
    String methodValid = "Valid";
    String methodCriterion = "Criterion";

    for (Method method : generatedCriteria.getMethods()) {
      String name = method.getName();
      if (name.endsWith(methodBetween)) continue;
      if (name.endsWith(methodNull)) continue;
      if (name.endsWith(methodIn)) continue;
      if (name.endsWith(methodCriteria)) continue;
      if (name.endsWith(methodValid)) continue;
      if (name.endsWith(methodCriterion)) continue;

      String origin = method.getBodyLines().get(0);
      origin = "if(value != null) ".concat(origin);
      method.getBodyLines().set(0, origin);
    }

    return true;
  }
}
