package com.bitcs.service;

import com.bitcs.VO.GoodsVO;
import com.bitcs.domain.OrderInfo;
import com.bitcs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 秒杀
 *
 * @author GeChen
 */
@Service
public class SpikeService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    /**
     * 原子操作：减库存 下订单 写入秒杀订单
     *
     * @param user
     * @param goodsVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo spike(User user, GoodsVO goodsVO) {
        //减库存
        goodsService.reduceStock(goodsVO);
        //下订单 order_info spike_order
        return orderService.createOrder(user, goodsVO);
    }
}
