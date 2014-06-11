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
    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN ="^[a-z0-9_-]{3,15}$";

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchRegisterRegular(String userName, String password, String verifyPassword) {

        if ( !password.equals(verifyPassword) ) {
            return false;
        }

        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    // This class organize two Dao to do one data transaction operation, that is update the table, t_user,
// and add one record into the table, t_login_log.
// All those transaction operations need to be configured by Spring declare transaction configuration.
    public void registerSuccess(User user) {
        user.setCredits(5);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.insertRegisterUserInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
