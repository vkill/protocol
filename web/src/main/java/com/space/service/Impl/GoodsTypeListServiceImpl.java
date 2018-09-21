package com.space.service.Impl;

import com.space.dao.GoodsTypeListRepository;
import com.space.entity.GoodsTypeList;
import com.space.service.GoodsTypeListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品服务层实现方法
 */
@Service("goodsTypeListService")
public class GoodsTypeListServiceImpl implements GoodsTypeListService {

    @Resource
    GoodsTypeListRepository goodsTypeListRepository;

    @Override
    public Map getGoodsListByType(String type) {
        Map resultMap = new HashMap();
        List<GoodsTypeList> allByTypeSign = goodsTypeListRepository.getAllByTypeSign(type);
        if (allByTypeSign.size() > 0) {
            resultMap.put("status","0");
            resultMap.put("dataList",allByTypeSign);
        } else {
            resultMap.put("status",1);
            resultMap.put("message","无此业务");
        }
        return resultMap;
    }
}
