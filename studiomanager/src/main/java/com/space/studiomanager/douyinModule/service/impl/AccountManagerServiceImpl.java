package com.space.studiomanager.douyinModule.service.impl;

import com.space.studiomanager.douyinModule.dao.AccountManagerRepository;
import com.space.studiomanager.douyinModule.service.AccountManagerService;
import com.space.studiomanager.entity.Company;
import com.space.studiomanager.entity.DYEntity;
import com.space.studiomanager.entity.PageEntity;
import com.space.studiomanager.utils.DateUtil;
import com.space.studiomanager.utils.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author space
 * @Date 2018-10-08
 * @Description: 帐号管理
 */
@Service("accountManagerService")
public class AccountManagerServiceImpl implements AccountManagerService {

    @Resource
    AccountManagerRepository accountManagerRepository;


    @Override
    public Page<DYEntity> getPageBySearch(PageEntity pageEntity, DYEntity dyEntity) {
        Pageable pageable = new PageRequest(pageEntity.getCurrentPage() - 1, pageEntity.getPageSize());
        Page<DYEntity> all = accountManagerRepository.findAll(getPageSpecification(pageEntity, dyEntity), pageable);
        return all;
    }

    @Override
    public Map getCurrentAndTotalCount() {
        Map map = new HashMap();
        String currentDate = DateUtil.getCurrentTime("yyyy-MM-dd");
        long count = accountManagerRepository.count();
        long today = accountManagerRepository.countDYEntityByRegisterDate(currentDate);
//        System.out.println("总数"+count);
//        System.out.println("今日" + today);
        map.put("today", today);
        map.put("total", count);
        return map;
    }


    private Specification<DYEntity> getPageSpecification(PageEntity pageEntity, DYEntity dyEntity) {
        return new Specification<DYEntity>() {
            @Override
            public Predicate toPredicate(Root<DYEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtil.isNotEmpty(pageEntity.getPhoneArea())) {
                    // 号码区域不为空
                    System.out.println(pageEntity.getPhoneArea());
                    predicates.add(cb.like(root.get("phoneArea").as(String.class), "%" + pageEntity.getPhoneArea() + "%"));
                }

                // 有无开始时间
                if (StringUtil.isNotEmpty(pageEntity.getStartTime())) {
                    if (StringUtil.isNotEmpty(pageEntity.getEndTime())) {
                        // 有开始和结束时间
                        predicates.add(cb.between(root.get("registerDate").as(String.class), pageEntity.getStartTime(), pageEntity.getEndTime()));
                    } else {
                        // 有开始无结束
                        predicates.add(cb.greaterThanOrEqualTo(root.get("registerDate").as(String.class), pageEntity.getStartTime()));
                    }
                } else {
                    // 这个情况是无开始时间，有结束时间
                    if (StringUtil.isNotEmpty(pageEntity.getEndTime())) {
                        predicates.add(cb.lessThanOrEqualTo(root.get("registerDate").as(String.class), pageEntity.getStartTime()));
                    }
                }
                Predicate[] p = new Predicate[predicates.size()];

                return cb.and(predicates.toArray(p));
            }
        };
    }
}
