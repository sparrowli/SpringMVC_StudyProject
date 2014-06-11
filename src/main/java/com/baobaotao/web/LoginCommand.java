// The Object, LoginCommand, is one object of POJO, which don't extend or implement one specific Father Class or interface.
// Declare this Controller in <servlet name>-servlet.xml, scan the Web path, and specify the view resolver of Spring MVC.
package com.baobaotao.web;

public class LoginCommand {
    private String userName;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
