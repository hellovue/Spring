package org.github.spring.mybatis.generator;

import java.util.TreeSet;

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
 * MyBatisGenerator批量更新(包含二进制数据)方法生成插件.
 *
 * @author JYD_XL
 * @see org.mybatis.generator.api.Plugin
 * @see org.mybatis.generator.api.PluginAdapter
 * @see com.freetmp.mbg.plugin.AbstractXmbgPlugin
 * @see org.github.spring.mybatis.generator.NaiveAbstractXMLPlugin
 */
public class BatchUpdateWithBLOBsPlugin extends NaiveAbstractXMLPlugin {
  private static final String BATCH_UPDATE = "batchUpdateWithBLOBs";
  private static final String PROPERTY_PREFIX = "item.";
  private static final Joiner JOINER = Joiner.on("").skipNulls();
  
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    if (introspectedTable.hasBLOBColumns()) {
      val importedTypes = new TreeSet<FullyQualifiedJavaType>();
      val method = new Method(BATCH_UPDATE);
      val objectType = introspectedTable.getRecordWithBLOBsType();
      val type = new FullyQualifiedJavaType(JOINER.join("java.util.List<", objectType, ">"));
      
      method.addParameter(new Parameter(type, "list"));
      method.setReturnType(FullyQualifiedJavaType.getIntInstance());
      
      importedTypes.add(type);
      
      interfaze.addMethod(method);
      interfaze.addImportedTypes(importedTypes);
    }
    
    return true;
  }
  
  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    if (introspectedTable.hasBLOBColumns()) {
      val update = new XmlElement("update");
      update.addAttribute(new Attribute("id", BATCH_UPDATE));
      val parameterType = "list";
      update.addAttribute(new Attribute("parameterType", parameterType));
      XmlElement choose = new XmlElement("choose");
      update.addElement(choose);
      XmlElement when = new XmlElement("when");
      when.addAttribute(new Attribute("test", "list != null and list.size != 0"));
      XmlElement otherwise = new XmlElement("otherwise");
      otherwise.addElement(new TextElement("SELECT 0"));
      choose.addElement(when);
      choose.addElement(otherwise);
      val foreach = new XmlElement("foreach");
      when.addElement(foreach);
      foreach.addAttribute(new Attribute("collection", "list"));
      foreach.addAttribute(new Attribute("item", "item"));
      foreach.addAttribute(new Attribute("index", "index"));
      foreach.addAttribute(new Attribute("separator", ";"));
      generateTextBlockAppendTableName(" update ", introspectedTable, foreach);
      val dynamicElement = new XmlElement("set");
      val objectType = introspectedTable.getNonPrimaryKeyColumns();
      generateParameterForSet(PROPERTY_PREFIX, true, objectType, dynamicElement);
      foreach.addElement(dynamicElement);
      generateWhereConditions(PROPERTY_PREFIX, introspectedTable.getPrimaryKeyColumns(), foreach);
      document.getRootElement().addElement(update);
    }
    
    return true;
  }
}
