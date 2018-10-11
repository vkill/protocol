package com.space.controller;

import com.util.SendOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dy_helper")
public class HelperController {

    /**
     * 转换成视频id
     * @param map
     * @return
     */
    @RequestMapping("/toVideoId")
    public Map toVideoId(@RequestBody Map map) {
        Map result = new HashMap();
        String videoUrl = (String) map.get("share_url");
        String videoId = SendOrder.getVideoId(videoUrl);
        String userId = SendOrder.share(videoId);
        if (videoId!=null && !videoId.equals("")) {
            result.put("status", "0");
            result.put("message","转换成功");
            result.put("video_id",videoId);
            result.put("user_id", userId);
        } else {
            result.put("status","1");
            result.put("message","转换失败， 请将分享链接复制到输入框");
        }
        return result;
    }

}
