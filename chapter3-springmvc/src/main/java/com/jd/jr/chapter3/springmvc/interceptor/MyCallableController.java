package com.jd.jr.chapter3.springmvc.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * User: 吴海旭
 * Date: 2016-11-11
 * Time: 下午5:19
 */
@Controller
public class MyCallableController {

    @RequestMapping("/myCallable1")
    @ResponseBody
    public Callable<String> callable1() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("====执行Callable call");
                return "success";
            }
        };
    }
    @RequestMapping("/myCallable2")
    @ResponseBody
    public Callable<String> callable2() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(11L * 1000);   // 配置的默认超时10秒
                System.out.println("====执行Callable call");
                return "success";
            }
        };
    }
}
