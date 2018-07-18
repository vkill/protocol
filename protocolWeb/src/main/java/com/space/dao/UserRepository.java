package com.space.dao;

import com.space.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据帐号密码返回
     * @param account
     * @param pwd
     * @return
     */
    @Query(value = "select * from t_user where account = ?1 and pwd = ?2", nativeQuery = true)
    User findByAccountAndPwd(String account, String pwd);
}
