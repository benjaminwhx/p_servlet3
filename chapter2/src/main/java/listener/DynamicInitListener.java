package listener;

import filter.DynamicFilter;
import servlet.DynamicServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * Created by wuhaixu on 2016/10/31.
 */
@WebListener
public class DynamicInitListener implements ServletContextListener{
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        /** @throws IllegalArgumentException if the given <tt>listenerClass</tt>
         * does not implement any of the above interfaces, or if it implements
         * {@link ServletContextListener} and this ServletContext was not passed
         * to {@link ServletContainerInitializer#onStartup}
         */
        // 这地方不能增加监听器
//        sc.addListener(DynamicServletContextListener.class);
        sc.addFilter("dynamicFilter", DynamicFilter.class);
        ServletRegistration.Dynamic dynamicServlet1 = sc.addServlet("dynamicServlet1", DynamicServlet.class);
        dynamicServlet1.setLoadOnStartup(1);
        dynamicServlet1.addMapping("/dynamic1");

        ServletRegistration.Dynamic dynamicServlet2 = sc.addServlet("dynamicServlet2", new DynamicServlet());
        dynamicServlet2.addMapping("/dynamic2");

        ServletRegistration.Dynamic dynamicServlet3 = sc.addServlet("dynamicServlet3", "servlet.DynamicServlet");
        dynamicServlet3.addMapping("/dynamic3");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}
