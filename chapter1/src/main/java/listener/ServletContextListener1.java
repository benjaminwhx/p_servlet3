package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by wuhaixu on 2016/10/31.
 */
@WebListener
public class ServletContextListener1 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("servlet context init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servlet context destroy");
    }
}
