package org.github.spring.configuration;

import org.github.spring.footstone.JSONMapperHolder;
import org.github.spring.footstone.XMLMapperHolder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.google.common.collect.ImmutableList;

import lombok.val;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter.PROTOBUF;

@Configuration
public class HttpMessageConverterConfiguration {
  @Bean
  public MappingJackson2HttpMessageConverter jacksonJSONHttpMessageConverter() {
    val jacksonJSONHttpMessageConverter = new MappingJackson2HttpMessageConverter(JSONMapperHolder.getJSONMapper());
    jacksonJSONHttpMessageConverter.setDefaultCharset(UTF_8);
    return jacksonJSONHttpMessageConverter;
  }

  @Bean
  public MappingJackson2XmlHttpMessageConverter jacksonXMLHttpMessageConverter() {
    val jacksonXMLHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter(XMLMapperHolder.getXMLMapper());
    jacksonXMLHttpMessageConverter.setDefaultCharset(UTF_8);
    return jacksonXMLHttpMessageConverter;
  }

  @Bean
  public ResourceHttpMessageConverter resourceHttpMessageConverter() {
    val resourceHttpMessageConverter = new ResourceHttpMessageConverter();
    resourceHttpMessageConverter.setDefaultCharset(UTF_8);
    return resourceHttpMessageConverter;
  }

  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    val protobufHttpMessageConverter = new ProtobufHttpMessageConverter();
    protobufHttpMessageConverter.setDefaultCharset(UTF_8);
    protobufHttpMessageConverter.setSupportedMediaTypes(ImmutableList.of(PROTOBUF));
    return protobufHttpMessageConverter;
  }

  @Bean
  public AllEncompassingFormHttpMessageConverter allEncompassingFormHttpMessageConverter() {
    val allEncompassingFormHttpMessageConverter = new AllEncompassingFormHttpMessageConverter();
    allEncompassingFormHttpMessageConverter.setCharset(UTF_8);
    allEncompassingFormHttpMessageConverter.setMultipartCharset(UTF_8);

    //TODO set order

    return allEncompassingFormHttpMessageConverter;
  }
}
