package com.space.register.dao;

import com.space.register.entity.DYUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DYUserRepository extends JpaRepository<DYUserEntity, Integer>, JpaSpecificationExecutor<DYUserEntity> {

}
