package org.github.spring.mybatis.generator;

import com.google.common.base.Joiner;

interface MybatisGeneratorPluginInterface {
  Joiner JOINER = Joiner.on("").skipNulls();
}