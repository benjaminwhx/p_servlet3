package com.jd.jr.chapter4.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午1:30
 */
@WebServlet(name = "changeSessionIdServlet", urlPatterns = "/changeSessionId")
public class ChangeSessionIdServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        // 可以访问 /createSession创建一个sessionId

        // 得到请求时的sessionId(请求时不一定带着)
        System.out.println("old=====" + req.getRequestedSessionId());

        // 调用时 必须有session 即request.getSession(false) != null 否则IllegalStateException
        req.changeSessionId();  // 触发MyHttpSessionIdListener

        System.out.println("new=====" + req.getRequestedSessionId());
    }
}
