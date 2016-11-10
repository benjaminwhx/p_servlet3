package com.jd.jr.chapter3.web.servlet.diapatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: 吴海旭
 * Date: 2016-11-10
 * Time: 下午4:24
 */
@WebServlet("/error-handler")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        Exception exception = (Exception)req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)req.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String)req.getAttribute("javax.servlet.error.request_uri");

        resp.setContentType("text/html;charset=utf-8");

        // print the output
        PrintWriter out = resp.getWriter();
        out.write("<html><head><title>Servlet 3 Exception Handling example</title></head><body>");
        if (statusCode != 500){
            out.write("<h3>Servlet 3 Exception Handling</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
        } else {
            out.write("<h3>Servlet 3 Exception Handling</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + exception.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + exception.getMessage() + "</li>");
            out.write("</ul>");
        }
        out.write("</body></html>");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
