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

import com.google.common.base.Joiner;

import lombok.val;

/**
 * MyBatisGenerator批量插入(包含二进制数据)方法生成插件.
 *
 * @author JYD_XL
 * @see org.mybatis.generator.api.Plugin
 * @see org.mybatis.generator.api.PluginAdapter
 * @see com.freetmp.mbg.plugin.AbstractXmbgPlugin
 * @see org.github.spring.mybatis.generator.NaiveAbstractXMLPlugin
 */
public class BatchInsertWithBLOBsPlugin extends NaiveAbstractXMLPlugin {
  /** 批量插入(包含二进制数据)方法名. */
  private static final String METHOD_NAME = "batchInsertWithBLOBs";
  /** MyBatisXML遍历属性前缀. */
  private static final String PROPERTY_PREFIX = "item.";
  /** 字符串拼接工具. */
  private static final Joiner JOINER = Joiner.on("").skipNulls();
  
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    //仅在包含二进制数据时生成.
    if (introspectedTable.hasBLOBColumns()) {
      val objectName = introspectedTable.getRecordWithBLOBsType();
      val type = new FullyQualifiedJavaType(JOINER.join("java.util.List<", objectName, ">"));
      
      val method = new Method(METHOD_NAME);
      method.addParameter(new Parameter(type, "list"));
      method.setReturnType(FullyQualifiedJavaType.getIntInstance());
      interfaze.addMethod(method);
    }
    
    return true;
  }
  
  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    //仅在包含二进制数据时生成.
    if (introspectedTable.hasBLOBColumns()) {
      val answer = new XmlElement("insert");
      answer.addAttribute(new Attribute("id", METHOD_NAME));
      val parameterType = new FullyQualifiedJavaType("list");
      answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
      XmlElement choose = new XmlElement("choose");
      answer.addElement(choose);
      XmlElement when = new XmlElement("when");
      when.addAttribute(new Attribute("test", "list != null and list.size != 0"));
      XmlElement otherwise = new XmlElement("otherwise");
      otherwise.addElement(new TextElement("SELECT 0"));
      choose.addElement(when);
      choose.addElement(otherwise);
      this.generateTextBlockAppendTableName("insert into ", introspectedTable, when);
      this.generateActualColumnNamesWithParenthesis(introspectedTable.getAllColumns(), when);
      this.generateTextBlock(" values ", when);
      val foreach = new XmlElement("foreach");
      foreach.addAttribute(new Attribute("collection", "list"));
      foreach.addAttribute(new Attribute("item", "item"));
      foreach.addAttribute(new Attribute("index", "index"));
      foreach.addAttribute(new Attribute("separator", ","));
      this.generateParametersSeparateByCommaWithParenthesis(PROPERTY_PREFIX, introspectedTable.getAllColumns(), foreach);
      when.addElement(foreach);
      document.getRootElement().addElement(answer);
    }
    return true;
  }
}
