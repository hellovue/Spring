package org.github.spring.module.index.action;

import org.github.spring.base.AbstractAction;
import org.github.spring.common.constant.HTMLPageEnum;
import org.github.spring.restful.Returnable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** @author JYD_XL */
@Controller
@RequestMapping("/")
public class IndexAction extends AbstractAction {
  /** index. */
  @GetMapping
  public Returnable index() {
    return HTMLPageEnum.INDEX;
  }

  /** star. */
  @GetMapping("star")
  public Returnable star() {return HTMLPageEnum.STAR;}
}
