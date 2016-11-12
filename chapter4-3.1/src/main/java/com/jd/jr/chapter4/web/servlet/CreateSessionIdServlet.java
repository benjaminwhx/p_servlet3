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
 * Time: 下午1:46
 */
@WebServlet(name = "createSessionIdServlet", urlPatterns = "/createSessionId")
public class CreateSessionIdServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // 得到当前session创建的id
        System.out.println("create session id: " + req.getSession().getId());

        // 不同于session的id,它是得到client请求时带过来的sessionId,可能为null
        System.out.println("get client session id: " + req.getRequestedSessionId());
    }
}
