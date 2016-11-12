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
 * Time: 下午2:30
 */
@WebServlet(name = "redirectServlet", urlPatterns = "/redirect")
public class RediredctServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 重定向
        resp.sendRedirect("//jd.com");
    }
}
