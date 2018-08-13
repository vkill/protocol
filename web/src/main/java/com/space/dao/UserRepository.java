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

    /**
     * 获取所有账号信息
     * @return
     */
    @Query(value = "select * from protocol.t_user", nativeQuery = true)
    List<User> findAllAccountAndPwd();

    /**
     * 获取一个账户信息
     * @param id
     * @return
     */
    @Query(value = "select * from t_user where id = ?1", nativeQuery = true)
    User getOne(int id);

    /**
     * 注册
     * @param id
     * @param account
     * @param pwd
     * @param balance
     */
    @Transactional
    @Query(value = "insert into protocol.t_user(id,account,pwd,balance) value(?1,?2,?3,?4)", nativeQuery = true)
    @Modifying
    void regist(int id, String account, String pwd,Double balance);

    /**
     * 充值
     * @param account
     * @param balance
     */
    @Transactional
    @Query(value = "update protocol.t_user set balance=?2 where account=?1", nativeQuery = true)
    @Modifying
    void recharge(int account,Double balance);

    /**
     * 消费
     * @param account
     * @param balance
     */
    @Transactional
    @Query(value = "update protocol.t_user set balance=?2 where account=?1", nativeQuery = true)
    @Modifying
    void consume(int account,Double balance);
}
