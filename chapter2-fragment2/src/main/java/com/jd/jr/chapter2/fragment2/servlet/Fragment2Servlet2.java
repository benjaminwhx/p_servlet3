package com.jd.jr.chapter2.fragment2.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-05
 * Time: 下午4:08
 */
@WebServlet(name = "fragment2Servlet2", urlPatterns = {"/fragment22"})
public class Fragment2Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("fragment2 servlet2 request");
    }
}
