package com.space.protocol.dao;


import com.space.protocol.entity.DouyinAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DouyinAccountRepository extends JpaRepository<DouyinAccount, Integer>, JpaSpecificationExecutor<DouyinAccount> {
}
