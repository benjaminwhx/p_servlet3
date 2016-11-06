package com.jd.jr.chapter3.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: 吴海旭
 * Date: 2016-11-06
 * Time: 下午4:58
 */
@WebServlet(name = "asyncServlet2", urlPatterns = "/async2", asyncSupported = true)
public class AsyncServlet2 extends HttpServlet {

    private final ExecutorService executorService = Executors.newScheduledThreadPool(2);

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // http1.1默认keep-alive
        resp.setHeader("Connection", "Keep-Alive");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.write("hello async");
        out.write("<br/>");
        out.flush();

        // 1、开启异步
        final AsyncContext asyncContext = req.startAsync();
        // 2、不设置默认jetty是3秒,设置0或者负数表示永远不超时
        asyncContext.setTimeout(10L * 1000);

        // 把任务提交给自己的任务队列
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3L * 1000);
                    PrintWriter out = asyncContext.getResponse().getWriter();
                    out.write("over2");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    asyncContext.complete();
                }
            }
        });
        // 4、当前线程立即返回
    }
}
