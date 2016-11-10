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
 * Time: 下午5:32
 */
@WebServlet(name = "dispatchServlet7", urlPatterns = "/dispatch7", asyncSupported = true)
public class DispatcherServlet7 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {


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
                System.out.println("=====async start");
            }
        });


        req.setAttribute("ok1", "true");
        req.setAttribute("msg", "success");
        try {
            Thread.sleep(1L * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncContext.dispatch();
        //多次调用dispatch，会抛出java.lang.IllegalStateException: REDISPATCHING,initial,resumed
        //目前jetty会死循环，建议try-catch 自己complete 或者实际一定要避免这么用
//        asyncContext.dispatch();
        System.out.println("===after dispatch before handle:" + req.isAsyncStarted());
    }
}
