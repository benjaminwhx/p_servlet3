package com.jd.jr.chapter3.web.servlet.listener;

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
 * Date: 2016-11-06
 * Time: 下午8:45
 */
@WebServlet(name = "listenerServlet2", urlPatterns = "/listener2", asyncSupported = true)
public class ListenerServlet2 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(5 * 1000);
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
        asyncContext.dispatch("/error"); //分派到一个不存在的地址 会报404，但是最终服务器会调用onComplete来完成异步
    }
}
