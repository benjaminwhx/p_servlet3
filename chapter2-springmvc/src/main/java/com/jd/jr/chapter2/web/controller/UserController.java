package com.jd.jr.chapter2.web.controller;

import com.jd.jr.chapter2.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: 吴海旭
 * Date: 2016-11-05
 * Time: 下午6:03
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String hello(Model model) {
        userService.sayHello();
        model.addAttribute("msg", "hello world");
        return "user/hello";
    }
}
