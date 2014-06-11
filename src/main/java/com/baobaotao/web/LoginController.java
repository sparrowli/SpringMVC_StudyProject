// This LoginController is responsible to handle the login request, finish the login operation,
// and redirect to welcome web or failure web by whether login successfully.

package com.baobaotao.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;

// Any POJO class annotated by this annotation, '@Controller', becomes a Controller of Spring MVC to handle the HTTP request.
// Note a class annotated by '@Controller' is a bean firstly, so '@Autowired' is able to inject.
// There are more handle methods responding different URL for one Controller, just by '@RequestMapping' specify the method how to
// map the responding URL like following methods.
@Controller
public class LoginController{

    @Autowired
    private UserService userService;

    // See to process the request, "/index.html".
    // The request param will be binded to the formal param of the responding method automatically according to the default contract of
    // the param name, such as in the method, loginCheck, the request param will be binded to its formal param,
    // 'loginCommand' due to the name match.
    @RequestMapping(value = "/index.html")
    public String loginPage(){
        return "login";
    }

    // See to process the request, "/loginCheck.html"

    // Request and respond method return a string or a object, 'ModelAndView'. Spring MVC will parse it and redirect the aim responding web.

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
        boolean isValidUser =
                userService.hasMatchUser(loginCommand.getUserName(),
                        loginCommand.getPassword());
        if (!isValidUser) {
            // First param is the logic name of view;
            // The other params is Data Model name and Data Model Object.
            // The model object put the model name as a param name into the property of request.
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            // ModelAndView contain both info of view and model data for render the view.
            return new ModelAndView("main");
        }
    }
}
