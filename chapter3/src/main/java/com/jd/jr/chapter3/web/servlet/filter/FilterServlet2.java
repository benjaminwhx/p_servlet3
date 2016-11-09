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
 * Time: 下午7:36
 */
@WebServlet(name = "filterServlet2", urlPatterns = "/filter2", asyncSupported = true)
public class FilterServlet2 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("====filter servlet 2  before");

        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(10L * 1000);
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(final AsyncEvent event) throws IOException {
                System.out.println("=====filter servlet2 async complete");
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

        System.out.println("====filter servlet 2  before dispatch");
        req.setAttribute("msg", "success");
        asyncContext.dispatch("/WEB-INF/jsp/dispatch.jsp");
        System.out.println("====filter servlet 2  after dispatch");

        System.out.println("====filter servlet 2  after");

    }
}