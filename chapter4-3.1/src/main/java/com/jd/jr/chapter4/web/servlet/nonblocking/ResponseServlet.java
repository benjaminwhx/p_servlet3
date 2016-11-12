package com.jd.jr.chapter4.web.servlet.nonblocking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午3:33
 */
@WebServlet(name = "responseServlet", urlPatterns = "/response")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("haha");

        resp.reset();

        resp.getOutputStream().write("wuwu".getBytes("ISO-8859-1"));

    }
}
