package com.space.service.Impl;

import com.space.dao.OrderTypeRepository;
import com.space.entity.OrderType;
import com.space.service.OrderTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderTypeService")
public class OrderTypeServiceImpl implements OrderTypeService {

    @Resource
    OrderTypeRepository orderTypeRepository;

    @Override
    public List<OrderType> getOrderTypeByPro(String projectName) {
        List<OrderType> result = orderTypeRepository.getAllByProjectType(projectName);
        return result;
    }

    @Override
    public Map getAllOrderType() {
        Map resultMap = new HashMap();
        List<OrderType> allType = orderTypeRepository.findAll();
        if (allType.size() > 0) {
            resultMap.put("status","0");
            resultMap.put("type_list", allType);
        } else {
            resultMap.put("status","1");
            resultMap.put("message","没有业务");
        }
        return resultMap;
    }

}
