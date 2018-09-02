package com.space.register.service.Impl;


import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.UrlRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("urlRequestService")
public class UrlRequestServiceImpl implements UrlRequestService {

    @Resource
    UrlRequestRepository urlRequestRepository;

    @Override
    public UrlRequestEntity getUrlRequest(int id) {
        UrlRequestEntity UrlRequestEntity = urlRequestRepository.findUrlById(id);
        Map a = UrlRequestEntity.getHeaderMap();
        System.out.println(a);
        return UrlRequestEntity;
    }
}
