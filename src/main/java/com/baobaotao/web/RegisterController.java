// This RegisterController is responsible to handle the register request, finish the sign in operation,
// and redirect to welcome web or failure web by whether sign in successfully.

package com.baobaotao.web;

import com.baobaotao.domain.User;
import com.baobaotao.service.RegisterService;
import com.baobaotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/registerCheck.html")
    public ModelAndView registerCheck(HttpServletRequest request, RegisterCommand registerCommand) {
        boolean isValidRegister =
                registerService.hasMatchRegisterRegular(registerCommand.getUserName(),
                        registerCommand.getPassword(),
                        registerCommand.getVerifyPassword());
        if (!isValidRegister) {
            // First param is the logic name of view;
            // The other params is Data Model name and Data Model Object.
            // The model object put the model name as a param name into the property of request.
            return new ModelAndView("register", "error", "用户名或密码错误。");
        } else {
            User user = new User();
            user.setUserName(registerCommand.getUserName());
            user.setPassword(registerCommand.getPassword());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            registerService.registerSuccess(user);
            request.getSession().setAttribute("user", user);
            // ModelAndView contain both info of view and model data for render the view.
            return new ModelAndView("login");
        }

    }

}
