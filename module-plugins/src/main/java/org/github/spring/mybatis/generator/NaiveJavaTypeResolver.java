package org.github.spring.mybatis.generator;

import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class NaiveJavaTypeResolver extends JavaTypeResolverDefaultImpl {
  public NaiveJavaTypeResolver() {
    this.forceBigDecimals = false;
  }
}
