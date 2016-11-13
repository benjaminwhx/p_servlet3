package com.jd.jr.chapter5.bigpipe;

import javax.servlet.http.HttpServletResponse;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:32
 */
public interface PageletView {

    public void render(final BigPipeContext context, final HttpServletResponse response);
}
