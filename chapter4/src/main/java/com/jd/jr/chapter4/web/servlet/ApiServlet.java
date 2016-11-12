package com.jd.jr.chapter4.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-11
 * Time: 下午6:36
 */
@WebServlet(name = "apiServlet", urlPatterns = "/api")
public class ApiServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // 以前的版本
        req.getSession().getServletContext();
        // 现在的版本
        ServletContext sc = req.getServletContext();

        // getMajorVersion和getMinorVersion分别返回当前servlet容器支持的Servlet规范最高版本和最低版本。
        // getEffectiveMajorVersion和getEffectiveMinorVersion分别返回当前应用基于的Servlet规范最高版本和最低版本，是servlet3.0规范增加的新特性。
        // 所以一般情况下：getMajorVersion>=getEffectiveMajorVersion>getEffectiveMinorVersion>=getMinorVersion。
        System.out.println(sc.getMajorVersion());
        // servlet主要版本
        System.out.println(sc.getEffectiveMajorVersion());
        //servlet次要版本
        System.out.println(sc.getEffectiveMinorVersion());


        //默认的session跟踪机制
        System.out.println(sc.getDefaultSessionTrackingModes());
        //有效的session跟踪机制
        System.out.println(sc.getEffectiveSessionTrackingModes());
        //设置session跟踪机制：有COOKIE URL SSL
        //需要在容器初始化时 完成 如ServletContextListener#contextInitialized方法中调用如下代码 具体看javadoc
//        Set<SessionTrackingMode> sessionTrackingModes = new HashSet<SessionTrackingMode>();
//        sessionTrackingModes.add(SessionTrackingMode.COOKIE);
//        sc.setSessionTrackingModes(sessionTrackingModes);

        //用于session跟踪的cookie配置，比如默认Name是JSESSIONID，可以修改之
        SessionCookieConfig sessionCookieConfig = sc.getSessionCookieConfig();
        System.out.println(sessionCookieConfig.getName());

        //把默认的JSESSIONID--修改为->id   可以观察客户端变成了id
//        sessionCookieConfig.setName("id");

        //得到请求的session id
        req.getRequestedSessionId();

        /**得到分派的类型 请参考：{@link javax.servlet.DispatcherType}*/
        System.out.println(req.getDispatcherType());


        Cookie cookie = new Cookie("key", "value");
        //servlet 3，功能是禁止客户端脚本访问
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);


        //得到响应的状态码
        System.out.println(resp.getStatus());
        System.out.println(resp.getHeaderNames());
    }
}
