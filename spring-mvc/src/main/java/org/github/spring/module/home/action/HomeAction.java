package org.github.spring.module.home.action;

import java.util.Map;

import javax.annotation.Resource;

import org.github.spring.annotation.Invoke;
import org.github.spring.base.AbstractAction;
import org.github.spring.common.constant.HTMLPageEnum;
import org.github.spring.module.home.model.TeacherCondModel;
import org.github.spring.module.home.service.IHomeService;
import org.github.spring.restful.MultiFileReturn;
import org.github.spring.restful.Returnable;
import org.github.spring.restful.json.JSONPReturn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * HomeAction.
 *
 * @author JYD_XL
 */
@Slf4j
@Controller
@RequestMapping("/home/")
public class HomeAction extends AbstractAction {
  @Resource
  private IHomeService homeService;

  /**
   * home.
   *
   * @return VIEWReturn
   */
  @GetMapping
  public Returnable home() {
    return HTMLPageEnum.HOME;
  }

  /**
   * search.
   *
   * @return JSONReturn
   */
  @GetMapping("ssm")
  public Returnable search(TeacherCondModel condModel) {
    return homeService.search(condModel);
  }

  /**
   * format.
   *
   * @return JSONReturn
   */
  @GetMapping("file")
  public Returnable format() {
    return MultiFileReturn.of("/**/*");
  }

  /**
   * lambda.
   *
   * @return JSONReturn
   */
  @PostMapping("lambda")
  public Returnable lambda(@Invoke Map<String, Object> param) {
    return Returnable.of(param.toString());
  }

  /**
   * holder.
   *
   * @return JSONReturn
   */
  @GetMapping("jsonp")
  public Returnable holder(String name) {
    return JSONPReturn.of("bilibili", "JYD_XL", 1, 2, 3, name);
  }
}
