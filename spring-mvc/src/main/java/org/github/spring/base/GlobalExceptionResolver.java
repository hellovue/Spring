package org.github.spring.base;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.spring.footstone.ConstInterface;
import org.github.spring.footstone.JSONMapperHolder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 错误信息统一处理. */
@Slf4j
public class GlobalExceptionResolver implements ConstInterface, HandlerExceptionResolver {
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + ex.getMessage());
        //这里有2种选择
        //跳转到定制化的错误页面
        /*ModelAndView error = new ModelAndView("error");
        error.addObject("exMsg", ex.getMessage());
		error.addObject("exType", ex.getClass().getSimpleName().replace("\"", "'"));*/
        //返回json格式的错误信息
        try {
            val writer = response.getWriter();
            val result = new BaseResult(false, ex.getMessage());
            writer.write(JSONMapperHolder.getJSONMapper().toJson(result));
            writer.flush();
        } catch (Exception e) {
            log.error("Exception:", e);
        }
        return null;
    }
}
