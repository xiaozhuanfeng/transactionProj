package com.example.demo.biz.service;

import com.example.demo.biz.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService2 {

    @Autowired
    private UserService userService;

    private User createUser(String userName){
        User user1 = new User();
        user1.setUserName(userName);
        user1.setPassWord("xiaoribiao001");
        user1.setAddress("长安");
        user1.setEmail("xiaoribiao@163.com");
        user1.setGender("1");
        user1.setIdentity("321736165502567129");
        return user1;
    }

    public void doHandleNoTrans_1(){
        User user1 = createUser("三狗子");
        userService.addRequiresNew(user1);
        User user2 = createUser("雅少");
        userService.addRequiresNew(user2);
        throw new RuntimeException();
    }

    public void doHandleNoTrans_2(){
        User user1 = createUser("三狗子");
        userService.addRequiresNew(user1);
        User user2 = createUser("雅少");
        userService.addRequiresNewException(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans_3(){
        User user1 = createUser("三狗子");
        userService.addRequired(user1);
        User user2 = createUser("雅少");
        userService.addRequiresNew(user2);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans_4(){
        User user1 = createUser("三狗子");
        userService.addRequired(user1);
        User user2 = createUser("雅少");
        userService.addRequiresNewException(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans_5(){
        User user1 = createUser("三狗子");
        userService.addRequired(user1);
        User user2 = createUser("雅少");
        try{
            userService.addRequiresNewException(user2);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
