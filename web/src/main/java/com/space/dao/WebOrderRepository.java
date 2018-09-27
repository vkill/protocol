package com.space.dao;

import com.space.entity.WebOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrderEntity, Integer>, JpaSpecificationExecutor<WebOrderRepository> {


    /**
     * 根据视频id，商品类型，和完成状态来判定用户能否下单
     * @param videoId
     * @param goods_type
     * @param status 0是完成，1是未完成
     * @return
     */
    @Query(value = "select * from t_web_order where video_id=?1 and goods_type=?2 and order_status=?3", nativeQuery = true)
    List<WebOrderEntity> hasOrder(String videoId,  String goods_type,int status);

}
