package com.jd.jr.chapter5.bigpipe.view.velocity;

import com.jd.jr.chapter5.bigpipe.BigPipeContext;
import org.apache.velocity.VelocityContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:37
 */
public class VelocityFramePageletView extends AbstractVelocityPageletView {

    @Override
    public void render(final BigPipeContext context, final HttpServletResponse response) {
        if(getContextType() != null) {
            response.setContentType(getContextType());
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
        }

        StringWriter sw = new StringWriter();

        getTemplate().merge(new VelocityContext(context), sw);

        out.write(sw.toString());
        out.flush();
    }
}
