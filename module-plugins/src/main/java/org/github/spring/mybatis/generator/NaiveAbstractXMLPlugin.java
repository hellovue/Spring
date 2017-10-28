package org.github.spring.mybatis.generator;

import java.util.List;

import com.freetmp.mbg.plugin.AbstractXmbgPlugin;

public abstract class NaiveAbstractXMLPlugin extends AbstractXmbgPlugin implements MybatisGeneratorPluginInterface {
  static final String PROPERTY_PREFIX = "item.";

  static final String PARAMETER_LIST = "list";

  @Override
  public boolean validate(List<String> warnings) {
    return warnings.isEmpty();
  }
}
