package com.bitcs.service;

import com.bitcs.VO.GoodsVO;
import com.bitcs.dao.OrderDao;
import com.bitcs.domain.OrderInfo;
import com.bitcs.domain.SpikeOrder;
import com.bitcs.domain.User;
import com.bitcs.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 订单
 *
 * @author GeChen
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    /**
     * 获取到秒杀订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    public SpikeOrder getSpikeOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getSpikeOrderByUserIdGoodsId(userId, goodsId);
    }

    /**
     * 生成订单
     *
     * @param user
     * @param goodsVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo createOrder(User user, GoodsVO goodsVO) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsPrice(goodsVO.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(OrderStatus.NOT_PAY.getValue());
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);
        SpikeOrder spikeOrder = new SpikeOrder();
        spikeOrder.setGoodsId(goodsVO.getId());
        spikeOrder.setOrderId(orderId);
        spikeOrder.setUserId(user.getId());
        orderDao.insertSpikeOrder(spikeOrder);
        return orderInfo;
    }
}
