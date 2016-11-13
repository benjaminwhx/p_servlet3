package com.jd.jr.chapter5.bigpipe;

import org.springframework.util.Assert;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:31
 */
public class PageletResult {

    private boolean isFrameResult = false;
    private String viewName;

    //非
    private String container;
    private String[] cssUrls;
    private String[] jsUrls;

    public static PageletResult frameResult(String viewName) {
        PageletResult framePageletResult = new PageletResult();
        framePageletResult.viewName = viewName;
        framePageletResult.isFrameResult = true;
        return framePageletResult;
    }

    public static PageletResult pageletResult(String viewName) {
        PageletResult framePageletResult = new PageletResult();
        framePageletResult.viewName = viewName;
        framePageletResult.isFrameResult = false;
        return framePageletResult;
    }

    public PageletResult container(String container) {
        Assert.isTrue(this.isFrameResult == false, "only be not frame result has container");
        this.container = container;
        return this;
    }

    public PageletResult cssUrl(String... cssUrls) {
        Assert.isTrue(this.isFrameResult == false, "only be not frame result has css url");
        this.cssUrls = cssUrls;
        return this;
    }
    public PageletResult jsUrl(String... jsUrls) {
        Assert.isTrue(this.isFrameResult == false, "only be not frame result has js url");
        this.jsUrls = jsUrls;
        return this;
    }


    public boolean isFrameResult() {
        return isFrameResult;
    }

    public String getViewName() {
        return viewName;
    }

    public String getContainer() {
        return container;
    }

    public String[] getCssUrls() {
        return cssUrls;
    }

    public String[] getJsUrls() {
        return jsUrls;
    }
}
