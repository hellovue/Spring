package org.github.spring.interceptor;

import org.github.spring.footstone.AbstractSpringComponent;
import org.github.spring.util.StringUtil;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ActionHandlerInterceptor.
 *
 * @author JYD_XL
 */
public class ActionHandlerInterceptor extends AbstractSpringComponent implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getParameterMap().entrySet().forEach(each -> each.setValue(clear(each.getValue())));
        return true;
    }

    /**
     * Clear.
     *
     * @param param String
     * @return String
     */
    private String clear(String param) {
        if (StringUtil.isBlank(param)) { return null; }
        param = param.replaceAll("<", "<");
        param = param.replaceAll(">", ">");
        param = param.replaceAll("\\(", "(");
        param = param.replaceAll("\\)", ")");
        param = param.replaceAll("'", "'");
        param = param.replaceAll("eval\\((.*)\\)", "");
        param = param.replaceAll("[\"'][\\s]*javascript:(.*)[\"']", "\"\"");
        return param;
    }

    /**
     * Clear.
     *
     * @param values String
     * @return String
     */
    private String[] clear(String[] values) {
        return Stream.of(values).parallel().map(this::clear).toArray(String[]::new);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {}
}
