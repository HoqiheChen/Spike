package com.bitcs.controller;

import com.bitcs.domain.User;
import com.bitcs.redis.RedisService;
import com.bitcs.redis.UserKey;
import com.bitcs.service.UserService;
import com.bitcs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 示例控制层
 *
 * @author GeChen
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "GeChen");
        return "hello";
    }

    @RequestMapping("/dbTest")
    public Result<User> getUser() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db")
    public Result<String> getTx() {
        return Result.success("");
    }

    @RequestMapping("/redisGet")
    public Result<User> redisGet() {
        User res = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(res);
    }

    @RequestMapping("/redisSet")
    public Result<Boolean> redisSet() {

        return Result.success(true);
    }
}
