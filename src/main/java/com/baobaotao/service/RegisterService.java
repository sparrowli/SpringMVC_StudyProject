package com.baobaotao.service;

import com.baobaotao.dao.LoginLogDao;
import com.baobaotao.dao.UserDao;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fli on 6/11/14.
 */

@Service
public class RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;


    public boolean hasMatchRegisterRegular(User user) {
        Pattern pattern;
        Matcher matcher;
        final String USERNAME_PATTERN ="^[a-z0-9_-]{3,15}$";

        System.out.println("xxxxxxxxxxxxxxxxxxx" + user.getUserName() + "=============" + user.getPassword()
        + user.getVerifyPassword());
        if ( !user.getPassword().equals( user.getVerifyPassword() ) ) {

            System.out.println(user.getPassword() + "=========" + user.getVerifyPassword());
            return false;
        }
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(user.getUserName());
        return matcher.matches();
    }

    // This class organize two Dao to do one data transaction operation, that is update the table, t_user,
// and add one record into the table, t_login_log.
// All those transaction operations need to be configured by Spring declare transaction configuration.
    public void createUser(User user) {

        user.setCredits(5);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        System.out.println("xxxxxxxxxxxxxxxxxxx" + user.getUserId() + "=============" + user.getLastIp()
                + user.getLastVisit());

        userDao.insertRegisterUserInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
