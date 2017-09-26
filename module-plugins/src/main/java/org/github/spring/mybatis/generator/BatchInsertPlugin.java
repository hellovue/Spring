package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class BatchInsertPlugin extends NaiveAbstractXMLPlugin {
  /** method name. */
  private final String name = "batchInsert";
  
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    String objectName = introspectedTable.getBaseRecordType();
    Method method = new Method(name);
    FullyQualifiedJavaType type = new FullyQualifiedJavaType(JOINER.join("java.util.List<", objectName, ">"));
    method.addParameter(new Parameter(type, "list"));
    method.setReturnType(FullyQualifiedJavaType.getIntInstance());
    interfaze.addMethod(method);
    return true;
  }
  
  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    XmlElement answer = new XmlElement("insert");
    
    answer.addAttribute(new Attribute("id", name));
    answer.addAttribute(new Attribute("parameterType", PARAMETER_LIST));
    XmlElement choose = new XmlElement("choose");
    answer.addElement(choose);
    
    XmlElement when = new XmlElement("when");
    when.addAttribute(new Attribute("test", "list != null and list.size != 0"));
    XmlElement otherwise = new XmlElement("otherwise");
    otherwise.addElement(new TextElement("SELECT 0"));
    choose.addElement(when);
    choose.addElement(otherwise);
    
    generateTextBlockAppendTableName("insert into ", introspectedTable, when);
    
    generateActualColumnNamesWithParenthesis(introspectedTable.getNonBLOBColumns(), when);
    
    generateTextBlock(" values ", when);
    
    XmlElement foreach = new XmlElement("foreach");
    foreach.addAttribute(new Attribute("collection", "list"));
    foreach.addAttribute(new Attribute("item", "item"));
    foreach.addAttribute(new Attribute("index", "index"));
    foreach.addAttribute(new Attribute("separator", ","));
    
    generateParametersSeparateByCommaWithParenthesis(PROPERTY_PREFIX, introspectedTable.getNonBLOBColumns(), foreach);
    
    when.addElement(foreach);
    
    document.getRootElement().addElement(answer);
    return true;
  }
}
