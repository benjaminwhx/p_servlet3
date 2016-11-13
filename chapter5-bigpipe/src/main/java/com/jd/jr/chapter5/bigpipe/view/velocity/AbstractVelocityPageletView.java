package com.jd.jr.chapter5.bigpipe.view.velocity;

import com.jd.jr.chapter5.bigpipe.PageletResult;
import com.jd.jr.chapter5.bigpipe.view.AbstractPageletView;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:36
 */
public abstract class AbstractVelocityPageletView extends AbstractPageletView {

    private VelocityEngine velocityEngine;
    private Template template;
    private PageletResult pageletResult;

    public PageletResult getPageletResult() {
        return pageletResult;
    }

    public void setPageletResult(final PageletResult pageletResult) {
        this.pageletResult = pageletResult;
    }

    @Override
    public void initApplicationContext(final ApplicationContext applicationContext) {
        super.initApplicationContext(applicationContext);
        if(velocityEngine == null) {
            setVelocityEngine(autodetectVelocityEngine());
        }
    }

    public void setVelocityEngine(final VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    protected VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    protected Template getTemplate() {
        if(template != null) {
            return template;
        }
        return getTemplate(getUrl());
    }

    protected Template getTemplate(String url) {
        if(getEncoding() != null) {
            return getVelocityEngine().getTemplate(url);
        }
        return getVelocityEngine().getTemplate(url, getEncoding());
    }

    private VelocityEngine autodetectVelocityEngine() {
        try {
            VelocityConfig velocityConfig = getApplicationContext().getBean(VelocityConfig.class);
            return velocityConfig.getVelocityEngine();
        }
        catch (NoSuchBeanDefinitionException ex) {
            throw new ApplicationContextException("please define a VelocityConfig", ex);
        }
    }
}
