package com.jd.jr.chapter3.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: 吴海旭
 * Date: 2016-11-09
 * Time: 下午6:50
 */
@WebServlet(name = "asyncServlet4", value = "/async4", asyncSupported = true)
public class AsyncServlet4 extends HttpServlet {

    private final Queue<AsyncContext> queue = new ConcurrentLinkedQueue<>();

    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模拟推送消息: 长轮询 + iframe长连接
                while (true) {
                    try {
                        Thread.sleep(1L * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //注意这种方式不能用于实际环境，因为没有考虑过期情况 所以每刷新一次页面会创建一个新的AsyncContext
                    Iterator<AsyncContext> iterator = queue.iterator();
                    while (iterator.hasNext()) {
                        AsyncContext asyncContext = iterator.next();
                        try {
                            ServletResponse response = asyncContext.getResponse();
                            PrintWriter out = response.getWriter();
                            String msg = "new msg: " + System.currentTimeMillis();
                            out.write(msg);
                            try {
                                response.flushBuffer();
                            } catch (Exception e) {
                                // 远程主机可能强制关闭一个连接,直接把连接移除
                                System.out.println("远程主机可能强制关闭一个连接,直接把连接移除" + e);
                            }
                            asyncContext.complete();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("push message count: " + queue.size());
                }
            }
        }).start();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Connection", "Keep-Alive");
        resp.setHeader("Cache-Control", "private");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("text/html;charset=utf-8");
        resp.flushBuffer();

        // 1、开启异步
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(final AsyncEvent event) throws IOException {
                queue.remove(event.getAsyncContext());
            }

            @Override
            public void onTimeout(final AsyncEvent event) throws IOException {
                // 超时做手动complete操作
                event.getAsyncContext().complete();
                queue.remove(event.getAsyncContext());
            }

            @Override
            public void onError(final AsyncEvent event) throws IOException {
                // 打印出异常信息
                System.out.println(event.getThrowable().getMessage());
                queue.remove(event.getAsyncContext());
            }

            @Override
            public void onStartAsync(final AsyncEvent event) throws IOException {
            }
        });

        // 2、把AsyncContext放入queue中,便于后面推送消息
        queue.add(asyncContext);
    }
}
