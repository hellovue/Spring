package org.github.spring.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableList;

@Component("restTemplate")
public class NaiveRestTemplate extends RestTemplate {
  /** Constructor. */
  @Autowired
  public NaiveRestTemplate(List<HttpMessageConverter<?>> httpMessageConverters) {
    this.setMessageConverters(ImmutableList.copyOf(httpMessageConverters));
  }
}
