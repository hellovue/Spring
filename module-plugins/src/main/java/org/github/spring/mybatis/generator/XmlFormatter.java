package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.dom.DefaultXmlFormatter;
import org.mybatis.generator.api.dom.xml.Document;

public class XmlFormatter extends DefaultXmlFormatter {
  @Override
  public String getFormattedContent(Document document) {
    return super.getFormattedContent(document).replaceAll(" {4}", "  ");
  }
}
