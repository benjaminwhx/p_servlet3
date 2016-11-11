package com.jd.jr.chapter3.springmvc.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * User: 吴海旭
 * Date: 2016-11-11
 * Time: 下午5:20
 */
@Controller
public class MyDefferedResultController {

    @RequestMapping("/myDefferedResult1")
    @ResponseBody
    public DeferredResult<String> myDefferedResult1() {
        final DeferredResult<String> result = new DeferredResult<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000L);
                } catch (InterruptedException e) {
                }
                System.out.println("设置DeferredResult的成功时结果");
                result.setResult("success");
            }
        }).start();
        return result;
    }

    @RequestMapping("/myDefferedResult2")
    @ResponseBody
    public DeferredResult<String> myDefferedResult2() {
        final DeferredResult<String> result = new DeferredResult<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(31 * 1000L);
                } catch (InterruptedException e) {
                }
                System.out.println("设置DeferredResult的成功时结果（将发生在异步任务完成后执行，因为超时了，结果无效）");
                result.setResult("success");
            }
        }).start();
        return result;
    }

}
