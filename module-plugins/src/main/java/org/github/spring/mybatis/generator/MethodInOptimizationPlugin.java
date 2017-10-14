package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class MethodInOptimizationPlugin extends NaivePluginAdapter {
  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    InnerClass criteria = this.getCriteria(topLevelClass);
    InnerClass generatedCriteria = this.getGeneratedCriteria(topLevelClass);
    if (generatedCriteria == null) return true;
    if (criteria == null) return true;
    
    String in = "In";
    String notIn = "NotIn";
    for (Method method : generatedCriteria.getMethods()) {
      String name = method.getName();
      if (! name.endsWith(in) && ! name.endsWith(notIn)) continue;
      
      String origin = method.getBodyLines().get(0);
      String line = "if (values != null && ! values.isEmpty()) ".concat(origin);
      method.getBodyLines().set(0, line);
    }
    
    return true;
  }
}
