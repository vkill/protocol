package com.space.studiomanager.douyinModule.utils;

import com.space.studiomanager.entity.DYEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: space
 * @Date: 2018/10/10 01:31
 * @Description: 用来转换格式
 */
public class FormatMap {

    /**
     * 将Page对象转换成前段接受的对象
     * @param page
     * @return
     */
    public static Map pageDataList(Page<DYEntity> page) {
        Map result = new HashMap();
        result.put("total", page.getTotalElements());
        result.put("pageSize", 100);
        result.put("currentPage", page.getNumber());
//        Map list = new HashMap();
        List list = new ArrayList();
        List<DYEntity> content = page.getContent();
        for (int i = 0; i < content.size(); i++) {
            Map dyEntity = new HashMap();
            dyEntity.put("id", content.get(i).getId());
            dyEntity.put("phoneArea", content.get(i).getPhoneArea());
            dyEntity.put("dyPhone", content.get(i).getPhoneNum());
            dyEntity.put("pwd", content.get(i).getPassword());
            dyEntity.put("date", content.get(i).getRegisterDate());
            dyEntity.put("diggCount", content.get(i).getEvent_id());
            list.add(dyEntity);

        }

        result.put("list", list);

        return result;
    }
}
//{
//        "status": "SUCCESS",
//        "data": {
//          "total": 10000,
//          "pageSize": 100,
//          "currentPage": 1,
//          "list": [
//          {
        //        "id": 1,
        //        "phoneArea": "+66",
        //        "dyPhone": "1234556",
        //        "pwd": "asd123456",
        //        "date": "2018-10-06",
        //        "diggCount": "100"
    //        }, {
        //        "id": 2,
        //        "phoneArea": "+855",
        //        "dyPhone": "1234556",
        //        "pwd": "asd123456",
        //        "date": "2018-10-06",
        //        "diggCount": "100"
//        }
//        ]
//        }
//        }

