package com.example.demo.biz;

import com.example.demo.biz.dao.UserMapper;
import com.example.demo.biz.dto.User;
import com.example.demo.biz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    DataSource dataSource;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void test1(){

        System.out.println("dataSource = "+dataSource);
        System.out.println("transactionTemplate = "+transactionTemplate);
    }

    @Test
    public void test2(){
        System.out.println(userMapper.getAll());
    }


    @Test
    public void test3(){
        User user1 = new User();
        user1.setUserName("哈哈龙");
        user1.setPassWord("hahalong001");
        user1.setAddress("长安");
        user1.setEmail("hahalong@163.com");
        user1.setGender("1");
        user1.setIdentity("321736165504567129");
        //int count = userMapper.insertUser(user1);
        int count = userMapper.updateUserByUserName(user1);
        System.out.println(count);
    }

    @Test
    public void test4(){
        userService.anotationTransControl4();
    }

    @Test
    public void test5(){
        new Thread(()->{userService.saveOrUpdateUser(3);}).start();
        new Thread(()->{userService.saveOrUpdateUser(4);}).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6(){
        new Thread(()->{userService.callProduce();}).start();
        new Thread(()->{userService.callProduce();}).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
