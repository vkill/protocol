package com.space.service.Impl;

import com.space.dao.OrderTypeRepository;
import com.space.entity.OrderType;
import com.space.service.OrderTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderTypeService")
public class OrderTypeServiceImpl implements OrderTypeService {

    @Resource
    OrderTypeRepository orderTypeRepository;

    @Override
    public List<OrderType> getOrderTypeByPro(String projectName) {
        List<OrderType> result = orderTypeRepository.getAllByProjectType(projectName);
        return result;
    }
}
