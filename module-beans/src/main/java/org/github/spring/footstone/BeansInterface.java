package org.github.spring.footstone;

public interface BeansInterface extends ConstInterface {
  /** profile -- development. */
  String PROFILE_DEV = "dev";
  
  /** profile -- test. */
  String PROFILE_QA = "qa";
  
  /** profile -- production. */
  String PROFILE_PRO = "pro";
  
  String BEAN_PROPERTIES_HOLDER = "org.springframework.context.support.PropertySourcesPlaceholderConfigurer#0";
  
  String PAGE_HELPER_INTERCEPTOR = "pageHelperInterceptor";

  String LOGGER_ASPECT = "loggerAspect";
  
  String MYSQL_DATA_SOURCE = "mysqlDataSource";
  
  String BEAN_MYBATIS_DATASOURCE = "myBatisDataSource";
  
  String BEAN_LOCAL_SESSION_FACTORY = "localSessionFactory";
  
  String BEAN_HIBERNATE_DATASOURCE = "hibernateDataSource";
  
  String SQL_SESSION_FACTORY = "sqlSessionFactory";
  
  String MAPPER_SCANNER = "mapperScanner";
  
  String BEAN_NAIVE_CONVERSION_SERVICE_FACTORY_BEAN = "naiveConversionServiceFactoryBean";
  
  String BEAN_ACTION_HANDLER_INTERCEPTOR = "actionHandlerInterceptor";
  
  String APPLICATION_CONTEXT_HOLDER = "applicationContextHolder";
  
  String SERVLET_CONTEXT_HOLDER = "servletContextHolder";
  
  String JACKSON_JSON_HTTP_MESSAGE_CONVERTER = "jacksonJSONHttpMessageConverter";
  
  String JACKSON_XML_HTTP_MESSAGE_CONVERTER = "jacksonXMLHttpMessageConverter";
  
  String CONVERSION_SERVICE_FACTORY = "conversionServiceFactory";
  
  String RESOURCE_HTTP_MESSAGE_CONVERTER = "resourceHttpMessageConverter";
  
  String NUMBER_FORMATTER = "numberFormatter";
  
  String DATETIME_FORMATTER = "dateTimeFormatter";
  
  String JSR310_DATETIME_FORMATTER = "jsr310DateTimeFormatter";
  
  String JODA_DATETIME_FORMATTER = "jodaDateTimeFormatter";
}
