package com.jd.jr.chapter4.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 上午10:10
 */
@WebListener
public class MyHttpSessionIdListener implements HttpSessionIdListener {

    @Override
    public void sessionIdChanged(final HttpSessionEvent event, final String oldSessionId) {
        System.out.println("========sessionId变更了,老的是: " + oldSessionId + ",新的是: " + event.getSession().getId());
    }
}
