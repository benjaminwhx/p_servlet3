package com.jd.jr.chapter4.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 上午8:47
 */
@WebServlet(name = "requestParameterServlet", urlPatterns = "/requestParameter")
public class RequestParameterServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        // 访问parameter.jsp 然后看结果
        System.out.println("name=====" + Arrays.toString(req.getParameterValues("name")));

        System.out.println(req.getQueryString());
    }
}
