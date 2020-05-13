package com.bitcs.controller;

import com.bitcs.domain.User;
import com.bitcs.service.UserService;
import com.bitcs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例控制层
 *
 * @author GeChen
 */
@RestController
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;

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
}
