package org.github.spring.mybatis.generator;

import java.util.List;

import com.freetmp.mbg.plugin.AbstractXmbgPlugin;
import com.google.common.base.Joiner;

public abstract class NaiveAbstractXMLPlugin extends AbstractXmbgPlugin {
  protected static final String PROPERTY_PREFIX = "item.";
  
  protected static final String PARAMETER_LIST = "list";
  
  protected static final Joiner JOINER = Joiner.on("").skipNulls();
  
  @Override
  public boolean validate(List<String> warnings) {
    return warnings.isEmpty();
  }
}
