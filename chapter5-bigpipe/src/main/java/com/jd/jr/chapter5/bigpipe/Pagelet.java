package com.jd.jr.chapter5.bigpipe;

import javax.servlet.http.HttpServletResponse;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:33
 */
public interface Pagelet {

    PageletResult run(final BigPipeContext context, final HttpServletResponse response);
}
