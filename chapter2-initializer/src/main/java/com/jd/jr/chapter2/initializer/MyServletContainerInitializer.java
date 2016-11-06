package com.jd.jr.chapter2.initializer;

import com.jd.jr.chapter2.initializer.servlet.DynamicServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * User: 吴海旭
 * Date: 2016-11-05
 * Time: 下午4:43
 */
public class MyServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("my servlet container initializer init");
        ServletRegistration.Dynamic dynamicServlet4 = ctx.addServlet("dynamicServlet4", DynamicServlet.class);
        dynamicServlet4.addMapping("/dynamic4");

        ctx.getServletRegistration("dynamicServlet4").addMapping("/dynamicServlet4");
    }
}
