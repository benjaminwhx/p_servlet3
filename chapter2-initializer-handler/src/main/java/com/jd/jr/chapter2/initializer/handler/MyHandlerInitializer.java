package com.jd.jr.chapter2.initializer.handler;

import com.jd.jr.chapter2.initializer.handler.servlet.AbstractHandlerServlet;
import com.jd.jr.chapter2.initializer.handler.servlet.HandlerServlet1;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * User: 吴海旭
 * Date: 2016-11-05
 * Time: 下午4:53
 */
@HandlesTypes(value = AbstractHandlerServlet.class)
public class MyHandlerInitializer implements ServletContainerInitializer {
    /**
     * my handler initializer init
     * [class com.jd.jr.chapter2.initializer.handler.servlet.HandlerServlet2, class com.jd.jr.chapter2.initializer.handler.servlet.HandlerServlet1]
     * com.jd.jr.chapter2.initializer.handler.servlet.HandlerServlet2: /handlerServlet2
     * com.jd.jr.chapter2.initializer.handler.servlet.HandlerServlet1: /handlerServlet1
     *
     * @param classSet
     * @param sc
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> classSet, ServletContext sc) throws ServletException {
        System.out.println("my handler initializer init");
        System.out.println(classSet);
        for (Class<?> c : classSet) {
            if (!(Modifier.isInterface(c.getModifiers()) || Modifier.isAbstract(c.getModifiers()))) {
                ServletRegistration.Dynamic dynamic = sc.addServlet(c.getName(), (Class<? extends AbstractHandlerServlet>) c);
                String pattern = "/" + c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
                dynamic.addMapping(pattern);
                System.out.println(c.getName() + ": " + pattern);
            }
        }
    }
}
