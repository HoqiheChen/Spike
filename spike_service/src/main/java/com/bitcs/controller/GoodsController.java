package com.bitcs.controller;

import com.bitcs.domain.User;
import com.bitcs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/to_list")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        return "goods_list";
    }
}
