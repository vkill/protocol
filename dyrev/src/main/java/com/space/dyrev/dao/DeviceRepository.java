package com.space.dyrev.dao;

import com.space.dyrev.commonentity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer>, JpaSpecificationExecutor<DeviceEntity> {

}
