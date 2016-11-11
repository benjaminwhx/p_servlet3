package com.jd.jr.chapter3.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Callable;

/**
 * User: 吴海旭
 * Date: 2016-11-11
 * Time: 下午5:54
 */
@Controller
public class WebAsyncTaskController {

    @RequestMapping("/webAsyncTask1")
    public WebAsyncTask<String> webAsyncTask1(final Model model) {
        long timeout = 10L * 1000; //自定义超时时间 10秒
        WebAsyncTask webAsyncTask = new WebAsyncTask(timeout, new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2L * 1000);
                model.addAttribute("msg", "hello web async task");
                String viewName = "msg";
                return viewName;
            }
        });
        return webAsyncTask;
    }

    @RequestMapping("/webAsyncTask2")
    public WebAsyncTask<String> webAsyncTask2(final Model model) {
        long timeout = 10L * 1000; //自定义超时时间 1秒
        WebAsyncTask webAsyncTask = new WebAsyncTask(timeout, new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2L * 1000);
                throw new RuntimeException("出错了");
            }
        });
        return webAsyncTask;
    }

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView("exception");
        mv.addObject("exception", e);
        return mv;
    }
}
