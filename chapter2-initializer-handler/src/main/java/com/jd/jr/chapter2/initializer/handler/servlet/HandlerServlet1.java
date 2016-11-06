package com.jd.jr.chapter2.initializer.handler.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-05
 * Time: 下午5:03
 */
public class HandlerServlet1 extends AbstractHandlerServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("chapter2 initializer handler servlet1 request");
    }
}
