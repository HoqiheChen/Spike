package com.bitcs.controller;

import com.bitcs.VO.GoodsVO;
import com.bitcs.domain.User;
import com.bitcs.service.GoodsService;
import com.bitcs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品
 *
 * @author GeChen
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVO> goodsList = goodsService.listGoodsVO();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }
}
