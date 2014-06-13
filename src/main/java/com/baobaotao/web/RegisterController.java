// This RegisterController is responsible to handle the register request, finish the sign in operation,
// and redirect to welcome web or failure web by whether sign in successfully.

package com.baobaotao.web;

import com.baobaotao.domain.User;
import com.baobaotao.service.RegisterService;
import com.baobaotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register.html")
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value = "/userCreated.html", method= RequestMethod.POST)
    public ModelAndView registerCheck(HttpServletRequest request, User user) {

        System.out.println(user.getPassword() + "===========" + user.getVerifyPassword());
        boolean isValidRegister =
                registerService.hasMatchRegisterRegular(user);
        if (!isValidRegister) {
            // First param is the logic name of view;
            // The other params is Data Model name and Data Model Object.
            // The model object put the model name as a param name into the property of request.
            return new ModelAndView("register", "error", "两次密码输入不一致，请重新输入。");
        } else {
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());

            if (userService.findUserByUserName(user.getUserName()).getUserName() != null) {
                return new ModelAndView("register", "error", "该用户名已被使用，请重新输入。");
            }

            registerService.createUser(user);
            request.getSession().setAttribute("user", user);
            // ModelAndView contain both info of view and model data for render the view.
            return new ModelAndView("createSuccess", "user", user);
        }

    }



}
