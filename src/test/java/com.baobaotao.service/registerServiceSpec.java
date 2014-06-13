package com.baobaotao.service;

import static org.junit.Assert.*;

import java.util.Date;

import com.baobaotao.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.baobaotao.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)

// Aslo provided by Spring, this annotation, '@ContextConfiguration' specify the configuration file of Spring.

// How to set the relative path simply?
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml"})

public class registerServiceSpec {
    @Autowired
    private RegisterService registerService;

    @Test
    public void hasMatchRegisterRegularSpec() {

        User user = new User();
        user.setUserName("thoughtworks");
        user.setPassword("123");
        user.setPassword("321");
        boolean b1 = registerService.hasMatchRegisterRegular(user);

        user.setUserName("thoughtworks");
        user.setPassword("12");
        user.setPassword("12");
        boolean b2 = registerService.hasMatchRegisterRegular(user);

        assertTrue(!b1);
        assertTrue(!b2);
    //    assertTrue(b3);

    }


    @Test
    public void createUserSpec() {
        User user = new User();
        user.setUserName("thoughtworks");
        user.setPassword("12345");
        user.setVerifyPassword("12345");
        user.setUserId(5);
        user.setLastIp("127.32.3.3");
        user.setLastVisit(new Date());

        registerService.createUser(user);
    }


}