package org.github.spring.module.home.model;

import lombok.Setter;
import org.github.spring.footstone.AbstractEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TeacherCondModel.
 * Bean and Method as unit.
 *
 * @author JYD_XL
 */
@Setter
public class TeacherCondModel extends AbstractEntity {
  /** number. */
  private BigDecimal number;

  /** number. */
  @DateTimeFormat(pattern = FORMAT_DATE)
  private Date datetime;
}
