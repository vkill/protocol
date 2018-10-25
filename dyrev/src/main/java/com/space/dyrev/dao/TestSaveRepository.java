package com.space.dyrev.dao;

import com.space.dyrev.commonentity.TestSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestSaveRepository extends JpaRepository<TestSave, Integer>, JpaSpecificationExecutor<TestSave> {



}
