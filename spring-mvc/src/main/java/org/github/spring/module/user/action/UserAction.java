package org.github.spring.module.user.action;

import org.github.spring.base.entity.UserEntity;
import org.github.spring.common.constant.JSPPageEnum;
import org.github.spring.module.user.action.model.UserCondModel;
import org.github.spring.module.user.service.IUserService;
import org.github.spring.restful.Returnable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by l on 7/1/17.
 */
@Controller
@RequestMapping("/jsp/")
public class UserAction {
    @Resource
    private IUserService userService;

    @GetMapping
    public Returnable index() {
        return JSPPageEnum.LOGIN;
    }

    @PostMapping("/loginCheck")
    public Returnable loginCheck(UserCondModel condModel, Model model, HttpServletRequest request) {
        boolean flag = userService.hasMatchUser(condModel.getUserName(), condModel.getPassword());
        if (flag) {
            UserEntity user = userService.findUserByUserName(condModel.getUserName());
            user.withLastIp(request.getRemoteAddr()).withLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return JSPPageEnum.MAIN;
        } else {
            model.addAttribute("error", "username or password error.");
            return JSPPageEnum.LOGIN;
        }
    }

}
