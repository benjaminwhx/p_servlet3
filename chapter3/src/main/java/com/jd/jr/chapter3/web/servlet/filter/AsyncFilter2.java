package com.jd.jr.chapter3.web.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-09
 * Time: 下午7:34
 */
@WebFilter(
        filterName = "asyncFilter2",
        urlPatterns = {"/filter1", "/filter2"},
        asyncSupported = true,
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ASYNC})
public class AsyncFilter2 implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        System.out.println("====before filter2");
        chain.doFilter(request, response);
        System.out.println("====after filter2");
    }

    @Override
    public void destroy() {
    }
}
