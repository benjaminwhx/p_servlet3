package com.jd.jr.chapter2.fragment2.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by benjamin吴海旭 on 2016/11/5.
 */
public class Fragment2Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("fragment2 filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
