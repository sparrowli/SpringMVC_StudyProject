package com.baobaotao.service;

import static org.junit.Assert.*;

import java.util.Date;

import com.baobaotao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.baobaotao.domain.User;

// Via the Junit4's annotation, '@RunWith', specify the test runner, SpringUnit4ClassRunner.class,
// provided by Spring for being able to integrate the container and Junit4 framework.
@RunWith(SpringJUnit4ClassRunner.class)

// Aslo provided by Spring, this annotation, '@ContextConfiguration' specify the configuration file of Spring.

// How to set the relative path simply?
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml"})

public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void matchUserSpec() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);

    }

    @Test
    public void userByUserNameSpec() {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void addLoginLogSpec() {
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}