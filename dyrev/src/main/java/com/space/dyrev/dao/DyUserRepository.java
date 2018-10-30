package com.space.dyrev.dao;

import com.space.dyrev.commonentity.DyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DyUserRepository extends JpaRepository<DyUserEntity, Integer>, JpaSpecificationExecutor<DyUserEntity> {


}
