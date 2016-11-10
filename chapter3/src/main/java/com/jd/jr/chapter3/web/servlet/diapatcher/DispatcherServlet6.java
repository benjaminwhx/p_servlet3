package com.jd.jr.chapter3.web.servlet.diapatcher;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-10
 * Time: 下午5:22
 */
@WebServlet(name = "dispatchServlet6", urlPatterns = "/dispatch6", asyncSupported = true)
public class DispatcherServlet6 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        //具体请参考servlet3.1规范中的第9章《转发请求》
        System.out.println(req.getAttribute(AsyncContext.ASYNC_CONTEXT_PATH));
        System.out.println(req.getAttribute(AsyncContext.ASYNC_REQUEST_URI));
        System.out.println(req.getAttribute(AsyncContext.ASYNC_PATH_INFO));
        // 原始路径中的查询参数
        System.out.println(req.getAttribute(AsyncContext.ASYNC_QUERY_STRING));
        System.out.println(req.getAttribute(AsyncContext.ASYNC_SERVLET_PATH));

        if(req.getAttribute("ok1") == null) {
            System.out.println("===before start async:" + req.isAsyncStarted());
            final AsyncContext asyncContext = req.startAsync();
            System.out.println("===after start async:" + req.isAsyncStarted());

            asyncContext.setTimeout(10L * 1000);
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(final AsyncEvent event) throws IOException {
                    System.out.println("=====async complete");
                }

                @Override
                public void onTimeout(final AsyncEvent event) throws IOException {
                }

                @Override
                public void onError(final AsyncEvent event) throws IOException {
                }

                @Override
                public void onStartAsync(final AsyncEvent event) throws IOException {
                }
            });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3L * 1000);
                    } catch (InterruptedException e) {
                    }
                    req.setAttribute("ok1", "true");
                    req.setAttribute("msg", "success");
                    asyncContext.dispatch("/dispatch6?test=1");
                    System.out.println("===after dispatch before handle:" + req.isAsyncStarted());
                }
            }).start();

            return;

        } else {
            //此处通过输出可以看到调用不再是异步的了，即经过dispatch后，处理不是异步的了
            System.out.println("===after dispatch in handling:" + req.isAsyncStarted());
            resp.getWriter().write((String)req.getAttribute("msg"));

        }
    }
}
