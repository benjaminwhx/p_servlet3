package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by wuhaixu on 2016/10/31.
 */
public class DynamicServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init dynamic servlet context listener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
