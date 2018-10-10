package com.space.studiomanager.douyinModule.controller;

import com.space.studiomanager.douyinModule.service.AccountManagerService;
import com.space.studiomanager.douyinModule.utils.FormatMap;
import com.space.studiomanager.entity.DYEntity;
import com.space.studiomanager.entity.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: space
 * @Date: 2018/10/9 21:56
 * @Description: dy帐号管理控制器
 */
@RestController
@RequestMapping("/api/actmgr")
public class AccountManagerController {

    @Resource
    AccountManagerService accountManagerService;

    /**
     * 分页获取数据
     * @return
     */
    @RequestMapping("/page")
    public Map getActByPage(int page, String phoneArea, String startTime, String endTime) {

        Map result = new HashMap();

        PageEntity pageEntity = new PageEntity();
        pageEntity.setCurrentPage(page);
        pageEntity.setPageSize(100);
        if (phoneArea != null && !phoneArea.equals("")) {
            pageEntity.setPhoneArea(phoneArea);
        }

        if (startTime != null && !startTime.equals("")) {
            pageEntity.setStartTime(startTime);
        }

        if (endTime != null && !endTime.equals("")) {
            pageEntity.setEndTime(endTime);
        }


        Page<DYEntity> pageBySearch = accountManagerService.getPageBySearch(pageEntity, null);
        if (pageBySearch!= null) {
            result.put("status", "SUCCESS");
            Map map = FormatMap.pageDataList(pageBySearch);
            result.put("data", map);
        }

        return result;
    }

    /**
     * 获取当前时间和所有的帐号
     * @return
     */
    @RequestMapping("/getCount")
    public Map getTodayAndTotalCount() {
        Map result = new HashMap();
        Map currentAndTotalCount = accountManagerService.getCurrentAndTotalCount();
        result.put("data", currentAndTotalCount);
        return result;
    }
}
