package com.jd.jr.chapter3.springmvc.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * DeferredResult：即延迟的结果，即延迟发送结果；其实和callable类似，但是可以使用其他线程协作来通知客户
 *
 * 可实现长轮询
 * User: 吴海旭
 * Date: 2016-11-11
 * Time: 下午5:39
 */
@Controller
public class DeferredResultController {

    private Queue<DeferredResult> queue = new ConcurrentLinkedQueue<>();

    @RequestMapping(value = "/message")
    @ResponseBody
    public DeferredResult<String> newMessage() {
        //1、创建DeferredResult
        long timeout = 10L * 1000;
        final DeferredResult<String> deferredResult = new DeferredResult<>(timeout);

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                queue.remove(deferredResult);
            }
        });

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                queue.remove(deferredResult);
            }
        });

        //2、加到消息队列，用于消息推送
        queue.add(deferredResult);
        return deferredResult;
    }

    @Scheduled(fixedRate = 5L * 1000)
    public void pushMessage() {
        Iterator<DeferredResult> iterator = queue.iterator();
        while (iterator.hasNext()) {
            DeferredResult<String> result = iterator.next();
            result.setResult("new Message: " + System.currentTimeMillis());
        }
    }
}
