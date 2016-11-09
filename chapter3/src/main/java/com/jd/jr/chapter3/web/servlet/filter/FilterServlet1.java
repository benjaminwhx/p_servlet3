package com.jd.jr.chapter3.web.servlet.filter;

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
 * Date: 2016-11-09
 * Time: 下午7:35
 */
@WebServlet(name = "filterServlet1", urlPatterns = "/filter1", asyncSupported = true)
public class FilterServlet1 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("====filter servlet 1  before");

        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(10L * 1000);
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(final AsyncEvent event) throws IOException {
                System.out.println("=====filter servlet1 async complete");
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

        System.out.println("====filter servlet 1  before dispatch");
        //异步下分派过去的请求，异步filter也可以拦截
        //如果filter不是DispatcherType.ASYNC 类型，那么分派到/filter2时，拦截器不会调用
        asyncContext.dispatch("/filter2");
        System.out.println("====filter servlet 1  after dispatch");

        System.out.println("====filter servlet 1  after");


    }
}