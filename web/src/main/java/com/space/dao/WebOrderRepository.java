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

    /**
     * 两个状态，未完成和进行中
     * @param videoId
     * @param goods_type
     * @param status
     * @param status2
     * @return
     */
    @Query(value = "select * from t_web_order where video_id=?1 and goods_type=?2 and (order_status=?3 or order_status=?4)", nativeQuery = true)
    List<WebOrderEntity> hasOrder(String videoId,  String goods_type,int status, int status2);



    /**
     * 根据id获取
     * @param videoId
     * @return
     */
    @Query(value = "select * from t_web_order where video_id = ?1", nativeQuery = true)
    List<WebOrderEntity> getAllByVideoId(String videoId);

    /**
     * 根据订单id获取所有订单
     * @param orderId
     * @return
     */
    @Query(value = "select * from t_web_order where order_number=?1", nativeQuery = true)
    List<WebOrderEntity> getAllByOrderId(String orderId);

    /**
     * 获取未付款的订单
     * @param isPay
     * @return
     */
    @Query(value = "select * from t_web_order where is_pay=?1 and order_status=?2", nativeQuery = true)
    List<WebOrderEntity> getAllByIsPay(int isPay, int orderStatus);

//    @Query(value = "select * from t_web_order where is_pay=?")
//    List<WebOrderEntity> getAllByIsPayAndOrderStatus(int ispay, int orderStatus);

}
