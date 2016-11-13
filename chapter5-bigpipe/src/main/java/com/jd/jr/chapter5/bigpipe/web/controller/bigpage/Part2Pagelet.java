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
@Controller("bigpipe/part2")
public class Part2Pagelet implements Pagelet {

    @Override
    public PageletResult run(final BigPipeContext context, final HttpServletResponse response) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }


        context.put("msg", "这是第二段内容");

        return PageletResult.pageletResult("part2")
                .container("part2")
                .cssUrl("/static/bigpipe/part2.css")
                .jsUrl("/static/bigpipe/part2.js");

    }
}
