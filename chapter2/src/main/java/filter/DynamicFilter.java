package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wuhaixu on 2016/10/31.
 */
public class DynamicFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init dynamic filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter dynamic filter");
    }

    @Override
    public void destroy() {
    }
}
