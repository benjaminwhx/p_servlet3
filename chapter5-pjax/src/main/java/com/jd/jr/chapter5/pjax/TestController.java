/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jd.jr.chapter5.pjax;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午5:52
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping()
    public String index() {
        return "test/index";
    }

    @RequestMapping("/page1")
    public String page1(@RequestHeader(value = "layout", defaultValue = "true") boolean layout, Model model) {
        model.addAttribute("layout", layout);
        return "test/page1";
    }
    @RequestMapping("/page2")
    public String page2(@RequestHeader(value = "layout", defaultValue = "true") boolean layout, Model model) {
        model.addAttribute("layout", layout);
        return "test/page2";
    }
    @RequestMapping("/page3")
    public String page3(@RequestHeader(value = "layout", defaultValue = "true") boolean layout, Model model) {
        model.addAttribute("layout", layout);
        return "test/page3";
    }
}
