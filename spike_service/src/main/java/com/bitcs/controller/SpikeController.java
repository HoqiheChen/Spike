package com.bitcs.controller;

import com.bitcs.VO.GoodsVO;
import com.bitcs.domain.OrderInfo;
import com.bitcs.domain.SpikeOrder;
import com.bitcs.domain.User;
import com.bitcs.service.GoodsService;
import com.bitcs.service.OrderService;
import com.bitcs.service.SpikeService;
import com.bitcs.util.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 秒杀
 *
 * @author GeChen
 */
@Controller
@RequestMapping("/miaosha")
public class SpikeController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SpikeService spikeService;

    @RequestMapping("do_miaosha")
    public String spike(Model model, User user,
                        @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        int stock = goodsVO.getGoodsStock();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.GOODS_LACK_ERROR.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀过
        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdGoodsId(user.getId(), goodsId);
        if (spikeOrder != null) {
            model.addAttribute("errmsg", CodeMsg.REPEAT_SPIKE_ERROR.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = spikeService.spike(user, goodsVO);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVO);
        return "order_detail";
    }
}
