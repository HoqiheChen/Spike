package com.bitcs.dao;

import com.bitcs.domain.OrderInfo;
import com.bitcs.domain.SpikeOrder;
import org.apache.ibatis.annotations.*;

/**
 * 订单
 *
 * @author GeChen
 */
@Mapper
public interface OrderDao {
    /**
     * 根据用户id和商品id得到秒杀订单的信息
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Select("select * from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    SpikeOrder getSpikeOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    /**
     * 插入一个商品订单
     *
     * @param orderInfo
     * @return
     */
    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    /**
     * 插入一个秒杀商品订单
     *
     * @param spikeOrder
     * @return
     */
    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertSpikeOrder(SpikeOrder spikeOrder);
}
