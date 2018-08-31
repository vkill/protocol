package com.space.register.dao;

import com.space.register.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer>, JpaSpecificationExecutor<DeviceEntity> {


}
