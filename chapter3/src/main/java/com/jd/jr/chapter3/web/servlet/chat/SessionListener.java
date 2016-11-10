package com.jd.jr.chapter3.web.servlet.chat;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * User: 吴海旭
 * Date: 2016-11-10
 * Time: 下午7:20
 */
@WebListener(value = "sessionListener")
public class SessionListener implements HttpSessionListener {

    private MsgPublisher msgPublisher = MsgPublisher.getInstance();

    @Override
    public void sessionCreated(final HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(50); //会话最长50秒
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent se) {
        msgPublisher.logout((String)se.getSession().getAttribute("username"));
    }
}
