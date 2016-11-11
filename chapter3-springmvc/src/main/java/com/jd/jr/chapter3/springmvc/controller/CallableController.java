package com.jd.jr.chapter3.springmvc.controller;

import com.jd.jr.chapter3.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Callable;

/**
 * spring实现方式：
 *   1、把任务提交给Executor 异步执行；
 *   2、执行完成后RequestMappingHandlerAdapter使用内部的ServletInvocableHandlerMethod包装返回值，即按照非异步方式再执行
 * User: 吴海旭
 * Date: 2016-11-11
 * Time: 下午5:34
 */
@Controller
public class CallableController {

    @RequestMapping("/callable1")
    public Callable<String> callable1(final Model model) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2L * 1000); //暂停两秒
                String viewName = "msg";
                model.addAttribute("msg", "hello callable");
                return viewName; //然后返回到指定视图页面
            }
        };
    }

    @RequestMapping("/callable2")
    public Callable<ModelAndView> callable2() {
        return new Callable<ModelAndView>() {
            @Override
            public ModelAndView call() throws Exception {
                Thread.sleep(2L * 1000); //暂停两秒
                ModelAndView mv = new ModelAndView("msg");
                mv.addObject("msg", "hello callable");
                return mv; //然后返回到指定视图页面
            }
        };
    }

    @RequestMapping("/callable3")
    @ResponseBody
    public Callable<Object> callable3() {
        return new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2L * 1000); //暂停两秒
                return new User(1, "zhang"); //返回json串
            }
        };
    }
}
