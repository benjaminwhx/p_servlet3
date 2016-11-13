package com.jd.jr.chapter5.bigpipe.view;

import com.jd.jr.chapter5.bigpipe.PageletView;
import org.springframework.context.ApplicationContext;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:36
 */
public abstract class AbstractPageletView implements PageletView {

    private ApplicationContext applicationContext;
    private String contextType;
    private String url;
    private String encoding;

    public void initApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public String getContextType() {
        return contextType;
    }

    public void setContextType(final String contextType) {
        this.contextType = contextType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }
}
