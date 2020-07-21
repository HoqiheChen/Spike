package com.bitcs.controller;

import com.bitcs.VO.GoodsVO;
import com.bitcs.domain.User;
import com.bitcs.service.GoodsService;
import com.bitcs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品
 *
 * @author GeChen
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @RequestMapping("/to_list")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVO> goodsList = goodsService.listGoodsVO();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, User user,
                         @PathVariable("goodsId") long goodsId) {
        logger.info("toDetail");
        model.addAttribute("user", user);
        GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int spikeStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {
            //秒杀还没开始，倒计时
            spikeStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {
            //秒杀结束
            spikeStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀进行中
            spikeStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", spikeStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
