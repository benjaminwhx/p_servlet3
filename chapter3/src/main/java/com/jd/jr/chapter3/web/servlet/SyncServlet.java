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
 * Date: 2016-11-06
 * Time: 下午8:39
 */
@WebServlet(name = "syncServlet", urlPatterns = "/sync")
public class SyncServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Connection", "Keep-Alive");
        resp.setHeader("Proxy-Connection", "Keep-Alive");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.write("hello sync");
        out.write("<br/>");
        out.flush();

        //假设是个耗时任务，此时必须等待
        try {
            Thread.sleep(2L * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        out.write("over");
    }
}
