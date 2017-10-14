package org.github.spring.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import static org.github.spring.mybatis.generator.NaivePluginAdapter.ANNOTATION_NULLABLE;
import static org.github.spring.mybatis.generator.NaivePluginAdapter.IMPORT_NULLABLE;

public class SelectByPrimaryKeyWithoutBLOBsMethodGeneratePlugin extends NaiveAbstractXMLPlugin {
  private static final String SELECT_BY_UNIQUE_KEY_WITHOUT_BLOBS = "selectByPrimaryKeyWithoutBLOBs";
  
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    if (introspectedTable.hasBLOBColumns() && introspectedTable.hasPrimaryKeyColumns()) {
      Method selectByUniqueKeyMethod = new Method(SELECT_BY_UNIQUE_KEY_WITHOUT_BLOBS);
      selectByUniqueKeyMethod.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
      selectByUniqueKeyMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()), "key"));
      selectByUniqueKeyMethod.addAnnotation(ANNOTATION_NULLABLE);
      interfaze.addImportedType(IMPORT_NULLABLE);
      interfaze.addMethod(selectByUniqueKeyMethod);
    }
    return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
  }
  
  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    if (introspectedTable.hasBLOBColumns() && introspectedTable.hasPrimaryKeyColumns()) {
      XmlElement root = new XmlElement("select");
      root.addAttribute(new Attribute("id", SELECT_BY_UNIQUE_KEY_WITHOUT_BLOBS));
      root.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
      root.addAttribute(new Attribute("parameterType", introspectedTable.getPrimaryKeyType()));
      root.addElement(new TextElement("select"));
      
      XmlElement include = new XmlElement("include");
      include.addAttribute(new Attribute("refid", "Base_Column_List"));
      Element from = new TextElement(JOINER.join("from ", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
      
      root.addElement(include);
      root.addElement(from);
      document.getRootElement().addElement(root);
      this.generateWhereConditions("key.", false, introspectedTable.getPrimaryKeyColumns(), root);
    }
    return super.sqlMapDocumentGenerated(document, introspectedTable);
  }
}
