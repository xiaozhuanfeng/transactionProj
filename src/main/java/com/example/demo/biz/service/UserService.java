package com.example.demo.biz.service;

import com.example.demo.biz.dao.UserMapper;
import com.example.demo.biz.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class UserService {
    @Autowired
    protected UserMapper userMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private int saveUser(int i){
        User user1 = new User();
        user1.setUserName("哈哈龙"+i);
        user1.setPassWord("hahalong001");
        user1.setAddress("长安");
        user1.setEmail("hahalong@163.com");
        user1.setGender("1");
        user1.setIdentity("321736165504567129");
        return userMapper.insertUser(user1);
    }

    private int updateUser(int i){
        User user1 = new User();
        user1.setUserName("哈哈龙"+i);
        user1.setPassWord("up-hahalong001");
        user1.setAddress("长安");
        user1.setEmail("up-hahalong@163.com");
        user1.setGender("1");
        user1.setIdentity("321736165504567129");
        return userMapper.updateUserByUserName(user1);
    }

    /**
     * 不做事务控制
     */
    public void withoutTransControl(){
        saveUser(1);
        int i = 6/0;
        updateUser(2);
    }

    /**
     * 编程式事务控制
     */
    public boolean programTransControl(){
        Boolean isSuccess = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            public Boolean doInTransaction(TransactionStatus status) {
                Boolean result = true;
                try {
                    saveUser(2);
                    updateUser(3);
                    int i = 6/0;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    result = false;
                }
                return result;
            }
        });
        return isSuccess;
    }

    @Transactional
    public void anotationTransControl1(){
        saveUser(2);
        int i = 6/0;
        updateUser(3);
    }

    @Transactional
    public void anotationTransControl2(){
        try{
            saveUser(2);
            int i = 6/0;
            updateUser(3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Transactional
    public void anotationTransControl3(){
        try{
            saveUser(3);
            int i = 6/0;
            updateUser(4);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Transactional
    public void anotationTransControl4(){
        try{
            saveUser(3);
            int i = 6/0;
            updateUser(4);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional
    public void saveOrUpdateUser(int i){
        User user1 = new User();
        user1.setId(55);
        user1.setUserName("哈哈龙"+i);
        user1.setPassWord("hahalong001");
        user1.setAddress("长安");
        user1.setEmail("hahalong@163.com");
        user1.setGender("1");
        user1.setIdentity("321736165504567129");
        userMapper.replaceIntoUser(user1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    //@Transactional
    public void callProduce(){
        userMapper.callProduce();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User user){
        userMapper.insertUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User user){
        userMapper.insertUser(user);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User user){
        userMapper.insertUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User user){
        userMapper.insertUser(user);
        throw new RuntimeException();
    }

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

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans(){
        User user1 = createUser("三狗子");
        addRequired(user1);
        User user2 = createUser("雅少");
        try{
            addRequiresNewException(user2);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans_bak1(){
        User user1 = createUser("三狗子");
        addRequired(user1);
        User user2 = createUser("雅少");

        try{
            addRequiredException(user2);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans_bak2(){
        User user1 = createUser("三狗子");
        addRequired(user1);
        User user2 = createUser("雅少");

        try{
            addRequired(user2);
            throw new RuntimeException();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doHandleTrans_bak3(){
        User user1 = createUser("三狗子");
        userMapper.insertUser(user1);
        User user2 = createUser("雅少");

        try{
            userMapper.insertUser(user2);
            throw new RuntimeException();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

}
