package org.github.spring.mybatis.generator;

import java.lang.reflect.Field;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodBetweenOptimizationPlugin extends NaivePluginAdapter {
  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    try {
      InnerClass criteria = this.getCriteria(topLevelClass);
      InnerClass generatedCriteria = this.getGeneratedCriteria(topLevelClass);
      if (generatedCriteria == null) return true;
      if (criteria == null) return true;
      
      String between = "Between";
      String notBetween = "NotBetween";
      for (Method method : generatedCriteria.getMethods()) {
        String name = method.getName();
        if (! name.endsWith(between) && ! name.endsWith(notBetween)) continue;
        
        Parameter head = method.getParameters().get(0);
        Class<?> headClass = head.getClass();
        Field headName = headClass.getDeclaredField("name");
        headName.setAccessible(true);
        headName.set(head, "head");
        
        Parameter tail = method.getParameters().get(1);
        Class<?> clazz = head.getClass();
        Field tailName = clazz.getDeclaredField("name");
        tailName.setAccessible(true);
        tailName.set(tail, "tail");
        
        String origin = method.getBodyLines().get(0);
        origin = "if(head != null && tail != null) ".concat(origin);
        String line = origin.replace("value1", "head").replace("value2", "tail");
        method.getBodyLines().set(0, line);
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      log.error(e.getMessage(), e);
    }
    
    return true;
  }
}
