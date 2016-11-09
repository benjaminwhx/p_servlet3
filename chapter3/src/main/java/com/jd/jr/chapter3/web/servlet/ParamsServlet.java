package com.jd.jr.chapter3.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: 吴海旭
 * Date: 2016-11-09
 * Time: 下午7:01
 */
@WebServlet(name = "p", urlPatterns = "/params")
public class ParamsServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("hello");
        writer.flush();
//        writer.close();

        System.out.println(req.getParameter("name"));
    }
}
