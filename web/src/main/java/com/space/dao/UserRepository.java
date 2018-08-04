package com.space.dao;

import com.space.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据帐号密码返回
     * @param account
     * @param pwd
     * @return
     */
    @Query(value = "select * from t_user where account = ?1 and pwd = ?2", nativeQuery = true)
    User findByAccountAndPwd(String account, String pwd);

    @Query(value = "select * from protocol.t_user", nativeQuery = true)
    List<User> findAllAccountAndPwd();

    @Transactional
    @Query(value = "insert into protocol.t_user(id,account,pwd) value(?1,?2,?3)", nativeQuery = true)
    @Modifying
    void regist(int id, String account, String pwd);

}
