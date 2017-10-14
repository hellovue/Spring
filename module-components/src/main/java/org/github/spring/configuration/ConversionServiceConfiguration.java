package org.github.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.google.common.collect.ImmutableSet;

import lombok.val;

@Configuration
public class ConversionServiceConfiguration {
  @Bean("conversionServiceFactory")
  public FormattingConversionServiceFactoryBean createConversionServiceFactory() {
    FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();
    val numberFormatter = new NumberFormatAnnotationFormatterFactory();
    val dateTimeFormatter = new DateTimeFormatAnnotationFormatterFactory();
    val jsr310DateTimeFormatter = new Jsr310DateTimeFormatAnnotationFormatterFactory();
    val jodaDateTimeFormatter = new JodaDateTimeFormatAnnotationFormatterFactory();
    conversionService.setFormatters(ImmutableSet.of(numberFormatter, dateTimeFormatter, jsr310DateTimeFormatter, jodaDateTimeFormatter));

    return conversionService;
  }
}
