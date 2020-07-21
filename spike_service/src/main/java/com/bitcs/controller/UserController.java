package com.bitcs.controller;

import com.bitcs.domain.User;
import com.bitcs.redis.RedisService;
import com.bitcs.service.UserService;
import com.bitcs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GeChen
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<User> info(Model model, User user) {
        return Result.success(user);
    }
}
