package com.example.demo.biz.dao;

import com.example.demo.biz.dto.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * xml  形式配置
 */
@Repository
public interface UserMapper {
    List<User> getAll();
    User getOne(int id);
    int insertUser(User user);

    int replaceIntoUser(User user);

    int insertOrUpdate(User user);

    void callProduce();

    int updateUserByUserName(User user);

    void deleteUserById(int id);

    /**
     * 动态sql
     * @param sql
     * @return
     */
    List<User> queyrUserList(String sql);

    /**
     * 动态sql
     * @param sql
     * @return
     */
    List<User> queyrUserList2(@Param("querySql") String sql);
}
