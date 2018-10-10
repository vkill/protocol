package com.space.studiomanager.douyinModule.service;

import com.space.studiomanager.entity.DYEntity;
import com.space.studiomanager.entity.PageEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface AccountManagerService {

    /**
     * 根据搜索和分页获取
     * @param pageEntity
     * @param dyEntity
     * @return
     */
    Page<DYEntity> getPageBySearch(PageEntity pageEntity, DYEntity dyEntity);

    /**
     * 获取当前时间和所有的计数
     * @return
     */
    Map getCurrentAndTotalCount();
}
