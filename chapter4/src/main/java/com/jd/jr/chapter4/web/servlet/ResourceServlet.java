package com.jd.jr.chapter4.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 上午9:15
 */
@WebServlet(name = "resourceServlet", urlPatterns = "/resource")
public class ResourceServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = req.getServletContext();

        // 查找顺序
        // 先相对于web应用的根查找
        // 找不到再到WEB-INF/lib jar包中的META-INF/resources下找(查找顺序不稳定)

        // 得到该路径下所有子路径
        System.out.println(sc.getResourcePaths("/"));
        // jar包里的
        System.out.println(sc.getResource("/t1.txt"));
        // webapp下的t3.txt
        System.out.println(sc.getResource("/t3.txt"));
        // jar包里的
        System.out.println(sc.getResource("/com/jd/jr/t2.txt"));
        // webapp下的t4.txt
        System.out.println(sc.getResource("/com/jd/jr/t4.txt"));

        // 另外可直接在浏览器中访问META-INF/resources下的资源
    }
}
