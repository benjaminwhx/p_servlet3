package com.jd.jr.chapter3.web.servlet.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-06
 * Time: 下午8:45
 */
@WebServlet(name = "listenerServlet1", urlPatterns = "/listener1", asyncSupported = true)
public class ListenerServlet1 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(2 * 1000);
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(final AsyncEvent event) throws IOException {
                System.out.println("=====async complete");
            }

            @Override
            public void onTimeout(final AsyncEvent event) throws IOException {
                System.out.println("=====async timeout");
                asyncContext.complete(); //需要调用下complete 否则如jetty默认每2秒重新调度一次当前方法
            }

            @Override
            public void onError(final AsyncEvent event) throws IOException {
                System.out.println("=====async error");
            }

            @Override
            public void onStartAsync(final AsyncEvent event) throws IOException {
                System.out.println("=====start new async");//第一次调用startAsync无用，而是与之后调用ServletRequest.startAsync时关联
            }
        });
    }
}
