package com.jd.jr.chapter5.bigpipe.view.velocity;

import com.jd.jr.chapter5.bigpipe.BigPipeContext;
import org.apache.velocity.VelocityContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:37
 */
public class VelocityPageletView extends AbstractVelocityPageletView {

    @Override
    public void render(final BigPipeContext context, final HttpServletResponse response) {
        StringWriter sw = new StringWriter();
        getTemplate() .merge(new VelocityContext(context), sw);
        StringBuilder buffer = new StringBuilder();
        /**
         *
         * <script>
         *     writePagelet({
         *         container : "", //html的容器
         *         html : "", //html内容
         *         css  : "", //要引入的css文件
         *         js : ""   // 要引入的js文件
         *
         *     });
         * </script>
         *
         */
        buffer.append("<script>pl.write(");
        buffer.append("{");
        buffer.append("container:\"");
        buffer.append(getPageletResult().getContainer());
        buffer.append("\",");
        buffer.append("html:\"");
        buffer.append(sw.toString().replaceAll("\"", "\\\\\"").replaceAll("[\r\n]", ""));
        buffer.append("\",");
        buffer.append("css:");
        appendArray(buffer, context.getContextPath(), getPageletResult().getCssUrls());
        buffer.append(",");
        buffer.append("js:");
        appendArray(buffer, context.getContextPath(), getPageletResult().getJsUrls());
        buffer.append("}");
        buffer.append(");</script>");
        try {
            response.getWriter().write(buffer.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendArray(StringBuilder buffer, String contextPath, String[] urls) {
        buffer.append("[");
        if(urls != null) {
            int index = 0;
            for(String url : urls) {
                if(index > 0) {
                    buffer.append(",");
                }
                buffer.append("\"");

                if(!url.startsWith("http:") && !url.startsWith("https:") && !url.startsWith(contextPath)) {
                    url = contextPath + url;
                }
                buffer.append(url);
                buffer.append("\"");
            }
        }
        buffer.append("]");
    }
}
