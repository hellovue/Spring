package org.github.spring.mybatis.generator;

import java.util.Optional;

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

public class SelectByUniqueKeyMethodGeneratePlugin extends NaiveAbstractXMLPlugin {
  private static final String SELECT_BY_UNIQUE_KEY = "selectByUniqueKey";
  
  private static final String SELECT_BY_UNIQUE_KEY_WITH_BLOBS = "selectByUniqueKeyWithBLOBs";
  
  private static final String SELECT_BY_EXAMPLE = "selectByExample";
  
  private static final String SELECT_BY_EXAMPLE_WITH_BLOBS = "selectByExampleWithBLOBs";
  
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.exceptions.TooManyResultsException"));
    Method method = new Method(SELECT_BY_UNIQUE_KEY);
    method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
    method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record"));
    method.addException(new FullyQualifiedJavaType("TooManyResultsException"));
    method.addAnnotation(ANNOTATION_NULLABLE);
    interfaze.addMethod(method);
    Optional.of(introspectedTable.hasBLOBColumns()).filter(Boolean.TRUE::equals).ifPresent(v -> this.generateSelectByUniqueKeyWithBLOBsMethod(interfaze, introspectedTable));
    
    interfaze.addImportedType(IMPORT_NULLABLE);
    return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
  }
  
  private void generateSelectByUniqueKeyWithBLOBsMethod(Interface interfaze, IntrospectedTable introspectedTable) {
    Method method = new Method(SELECT_BY_UNIQUE_KEY_WITH_BLOBS);
    method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType()));
    method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record"));
    method.addException(new FullyQualifiedJavaType("TooManyResultsException"));
    method.addAnnotation(ANNOTATION_NULLABLE);
    interfaze.addMethod(method);
  }
  
  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    XmlElement root = new XmlElement("select");
    root.addAttribute(new Attribute("id", SELECT_BY_UNIQUE_KEY));
    root.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
    root.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
    
    root.addElement(new TextElement("select"));
    XmlElement include = new XmlElement("include");
    include.addAttribute(new Attribute("refid", "Base_Column_List"));
    Element from = new TextElement(JOINER.join("from ", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
    root.addElement(include);
    root.addElement(from);
    document.getRootElement().addElement(root);
    this.generateWhereConditions("record.", true, introspectedTable.getBaseColumns(), root);
    
    Optional.of(introspectedTable.hasBLOBColumns()).filter(Boolean.TRUE::equals).ifPresent(v -> this.generateSelectByUniqueKeyWithBLOBsElement(document, introspectedTable));
    return super.sqlMapDocumentGenerated(document, introspectedTable);
  }
  
  private void generateSelectByUniqueKeyWithBLOBsElement(Document document, IntrospectedTable introspectedTable) {
    XmlElement root = new XmlElement("select");
    root.addAttribute(new Attribute("id", SELECT_BY_UNIQUE_KEY_WITH_BLOBS));
    root.addAttribute(new Attribute("resultMap", introspectedTable.getResultMapWithBLOBsId()));
    root.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
    
    root.addElement(new TextElement("select"));
    
    XmlElement includeBase = new XmlElement("include");
    includeBase.addAttribute(new Attribute("refid", "Base_Column_List"));
    
    XmlElement includeBlob = new XmlElement("include");
    includeBlob.addAttribute(new Attribute("refid", "Blob_Column_List"));
    
    root.addElement(includeBase);
    root.addElement(new TextElement(","));
    root.addElement(includeBlob);
    
    Element from = new TextElement(JOINER.join("from ", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
    root.addElement(from);
    
    document.getRootElement().addElement(root);
    this.generateWhereConditions("record.", true, introspectedTable.getBaseColumns(), root);
  }
}
