package com.baobaotao.web;
// The Object, RegisterCommand, is one object of POJO, which don't extend or implement one specific Father Class or interface.
// Declare this Controller in <servlet name>-servlet.xml, scan the Web path, and specify the view resolver of Spring MVC.


public class RegisterCommand {

    private LoginCommand loginCommand;

    private String verifyPassword;

    public RegisterCommand() {
        loginCommand = new LoginCommand();
    }

    public void setVerifyPassword(String verifyPassword) { this.verifyPassword = verifyPassword; }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setPassword(String password) {
        loginCommand.setPassword(password);
    }

    public String getPassword() {
        return loginCommand.getPassword();
    }

    public String getUserName() {
        return loginCommand.getUserName();
    }

    public void setUserName(String userName) {
        loginCommand.setUserName(userName);
    }
}
