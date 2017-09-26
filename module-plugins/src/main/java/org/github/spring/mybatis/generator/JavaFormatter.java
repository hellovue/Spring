package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;

public class JavaFormatter extends DefaultJavaFormatter {
  @Override
  public String getFormattedContent(CompilationUnit compilationUnit) {
    return super.getFormattedContent(compilationUnit).replaceAll(" {4}", "  ");
  }
}
