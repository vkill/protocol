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
     * @param regist_id
     * @return
     */
    @Query(value = "select * from t_user where regist_id = ?1", nativeQuery = true)
    User getOne(String regist_id);

    /**
     * 注册
     * @param account
     * @param pwd
     * @param balance
     */
    @Transactional
    @Query(value = "insert into protocol.t_user(account,pwd,balance,regist_id) value(?1,?2,?3,?4)", nativeQuery = true)
    @Modifying
    void regist(String account, String pwd,Double balance,String registId);

    /**
     * 充值
     * @param regist_id
     * @param balance
     */
    @Transactional
    @Query(value = "update protocol.t_user set balance=?2 where regist_id=?1", nativeQuery = true)
    @Modifying
    void recharge(String regist_id,Double balance);

    /**
     * 消费
     * @param regist_id
     * @param balance
     */
    @Transactional
    @Query(value = "update protocol.t_user set balance=?2 where regist_id=?1", nativeQuery = true)
    @Modifying
    void consume(String regist_id,Double balance);
}
