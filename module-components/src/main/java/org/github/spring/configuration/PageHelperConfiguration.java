package org.github.spring.configuration;

import java.io.IOException;
import java.util.Properties;

import org.github.spring.footstone.BeansInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.github.pagehelper.PageInterceptor;

@Configuration
public class PageHelperConfiguration implements BeansInterface {
  @Bean(PAGE_HELPER_INTERCEPTOR)
  public PageInterceptor pageHelperInterceptor() throws IOException {
    Resource resource = new ClassPathResource("pageHelper.properties");
    PageInterceptor pageHelper = new PageInterceptor();
    Properties properties = new Properties();
    properties.load(resource.getInputStream());
    pageHelper.setProperties(properties);

    return pageHelper;
  }
}
