package com.jd.jr.chapter5.bigpipe.web.controller.bigpage;

import com.jd.jr.chapter5.bigpipe.BigPipeContext;
import com.jd.jr.chapter5.bigpipe.Pagelet;
import com.jd.jr.chapter5.bigpipe.PageletResult;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:39
 */
@Controller("bigpipe/index")
public class IndexPagelet implements Pagelet {

    @Override
    public PageletResult run(final BigPipeContext context, final HttpServletResponse response) {

        context.put("msg", "首页主要内容");

        return PageletResult.frameResult("index");
    }
}
