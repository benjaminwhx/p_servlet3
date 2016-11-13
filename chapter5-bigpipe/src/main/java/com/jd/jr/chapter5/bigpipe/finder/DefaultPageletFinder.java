package com.jd.jr.chapter5.bigpipe.finder;

import com.jd.jr.chapter5.bigpipe.Pagelet;
import com.jd.jr.chapter5.bigpipe.PageletFinder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:30
 */
public class DefaultPageletFinder implements PageletFinder, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Pagelet find(final String pageletName) {
        return this.applicationContext.getBean(pageletName, Pagelet.class);
    }
}
