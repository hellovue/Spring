package org.github.spring.resolver;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.spring.footstone.AbstractSpringComponent;
import org.github.spring.restful.Returnable;
import org.github.spring.restful.json.JSONBasicReturn;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableList;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * SystemExceptionResolver.
 *
 * @author JYD_XL
 * @see java.io.Serializable
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.web.context.ServletContextAware
 * @see org.github.spring.footstone.AbstractEntity
 * @see AbstractSpringComponent
 * @see org.springframework.beans.factory.Aware
 * @see org.springframework.beans.factory.BeanNameAware
 * @see org.springframework.web.servlet.HandlerExceptionResolver
 */
@Slf4j
public class SystemExceptionResolver extends AbstractSpringComponent implements HandlerExceptionResolver {
  /** resolver service. */
  private static final List<ExceptionResolver<?>> RESOLVER_SERVICE;

  @SuppressWarnings("unchecked")
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    Returnable returnable;
    for (ExceptionResolver resolver : RESOLVER_SERVICE) {
      returnable = resolver.resolve(request, response, ex);
      if (Objects.nonNull(returnable)) {
        try {
          returnable.collect(request, response);
        } catch (IOException e) {
          log.error(e.getMessage(), e);
        }
        return null;
      }
    }
    return null;
  }

  @FunctionalInterface
  private interface ExceptionResolver<E extends Exception> {
    Returnable resolve(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull E ex);
  }

  static {
    ExceptionResolver<Exception> otherExceptionResolver = (q, p, e) -> JSONBasicReturn.error();
    ExceptionResolver<NullPointerException> nullPointerExceptionResolver = (q, p, e) -> Optional.ofNullable(e.getMessage()).filter(v -> ! EMPTY.equals(v) && ! PATTERN_NULL.matcher(v).matches() && PATTERN_PARAM.matcher(v).matches()).map(JSONBasicReturn::errorOfNullParam).orElse(null);
    RESOLVER_SERVICE = ImmutableList.of(nullPointerExceptionResolver, otherExceptionResolver);
  }
}
