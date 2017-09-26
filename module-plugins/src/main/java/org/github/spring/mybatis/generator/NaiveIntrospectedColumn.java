package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;

public class NaiveIntrospectedColumn extends IntrospectedColumn {
  @Override
  public void setColumnNameDelimited(boolean isColumnNameDelimited) {
    super.setColumnNameDelimited(isColumnNameDelimited);
  }
}
