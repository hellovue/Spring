package org.github.spring.mybatis.generator;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.plugins.RenameExampleClassPlugin;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * NaiveRenameClassPlugin.
 *
 * @author JYD_XL
 */
@Slf4j
public class NaiveRenameClassPlugin extends RenameExampleClassPlugin {
  @Override
  public boolean validate(List<String> warnings) {
    return warnings.isEmpty();
  }
  
  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    String oldKey = introspectedTable.getPrimaryKeyType();
    String oldExample = introspectedTable.getExampleType();
    String oldMapper = introspectedTable.getMyBatis3JavaMapperType();
    String oldBLOBs = introspectedTable.getRecordWithBLOBsType();
    
    String[] packagePart = oldKey.split("\\.");
    String packageName = packagePart[packagePart.length - 2];
    String regex = "." + packageName + ".";
    
    introspectedTable.setPrimaryKeyType(oldKey.replace(regex, ".key."));
    introspectedTable.setBaseRecordType(introspectedTable.getBaseRecordType().concat("Entity"));
    introspectedTable.setExampleType(oldExample.replace(regex, ".example."));
    introspectedTable.setRecordWithBLOBsType(oldBLOBs.replace(regex, ".blob."));
    if (nonNull(oldMapper)) introspectedTable.setMyBatis3JavaMapperType(oldMapper.replace("Dao", "Mapper"));
    
    String dbName = introspectedTable.getContext().getProperty("dataBaseName");
    if (isNull(dbName)) return;
    String tableNameOld = introspectedTable.getFullyQualifiedTableNameAtRuntime();
    String tableNameNew = dbName.concat(".").concat(tableNameOld);
    introspectedTable.setSqlMapFullyQualifiedRuntimeTableName(tableNameNew);
    introspectedTable.setSqlMapAliasedFullyQualifiedRuntimeTableName(tableNameNew);
  }
  
  @Override
  public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    if (introspectedTable.hasBaseColumns()) topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
    return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
  }
  
  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    if (introspectedTable.hasPrimaryKeyColumns() && introspectedTable.getContext().getDefaultModelType().equals(ModelType.HIERARCHICAL)) topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()));
    return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
  }
}
