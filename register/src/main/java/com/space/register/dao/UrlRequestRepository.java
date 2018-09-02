package com.space.register.dao;

import com.space.register.entity.UrlRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UrlRequestRepository extends JpaRepository<UrlRequestEntity, Integer>, JpaSpecificationExecutor<UrlRequestEntity> {
}
