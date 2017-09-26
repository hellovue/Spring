package org.github.spring.template;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("restTemplate")
public class NaiveRestTemplate extends RestTemplate {
  /** Constructor. */
  @Autowired
  public NaiveRestTemplate(List<HttpMessageConverter<?>> httpMessageConverters) {this.setMessageConverters(ImmutableList.copyOf(httpMessageConverters));}
}
