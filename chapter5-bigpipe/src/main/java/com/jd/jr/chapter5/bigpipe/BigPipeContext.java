package com.jd.jr.chapter5.bigpipe;

import java.util.HashMap;
import java.util.Map;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:31
 */
public class BigPipeContext extends HashMap {

    private final String contextPath;

    public BigPipeContext(final String contextPath, final Map model) {
        this.contextPath = contextPath;
        put("contextPath", contextPath);
        if(model != null) {
            this.putAll(model);
        }
    }

    public String getContextPath() {
        return contextPath;
    }

    public BigPipeContext copy() {
        BigPipeContext newContext = new BigPipeContext(this.contextPath, null);
        newContext.putAll(this);
        return newContext;
    }
}
