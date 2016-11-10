package com.jd.jr.chapter3.web.servlet.chat;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * User: 吴海旭
 * Date: 2016-11-10
 * Time: 下午7:05
 */
public class MsgPublisher {

    private static MsgPublisher publisher = new MsgPublisher();
    private volatile Map<String, Queue<AsyncContext>> usernameToAsyncContextMap = new ConcurrentHashMap<>();

    private MsgPublisher(){}

    public static MsgPublisher getInstance() {
        return publisher;
    }

    public Collection<String> getLoginUsers() {
        return new HashSet(usernameToAsyncContextMap.keySet());
    }

    public void startAsync(final HttpServletRequest req, final String userName) {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(30L * 1000);

        // 将异步上下文放在队列中，这样以后可以进行推送消息
        Queue<AsyncContext> queue = usernameToAsyncContextMap.get(userName);
        if (queue == null) {
            queue = new ConcurrentLinkedDeque<>();
            usernameToAsyncContextMap.put(userName, queue);
        }

        queue.add(asyncContext);

        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                Queue<AsyncContext> queue = usernameToAsyncContextMap.get(userName);
                if (queue != null) {
                    queue.remove(event.getAsyncContext());
                }
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                event.getAsyncContext().complete();
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                Queue<AsyncContext> queue = usernameToAsyncContextMap.get(userName);
                if (queue != null) {
                    queue.remove(event.getAsyncContext());
                }
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
            }
        });
    }

    /**
     *
     * @param receiver 如果为空 表示发送给所有人
     * @param sender
     * @param data
     */
    private void publish(String receiver, String sender, String data) {
        if (receiver == null || receiver.trim().length() == 0) {//发送给所有人
            for (String loginUsername : usernameToAsyncContextMap.keySet()) {
                if (loginUsername.equals(sender)) {
                    continue;
                }
                Queue<AsyncContext> queue = usernameToAsyncContextMap.get(loginUsername);
                writeData(queue, data);
            }
        } else { //私人消息
            Queue<AsyncContext> queue = usernameToAsyncContextMap.get(receiver);
            writeData(queue, data);
        }
    }

    private void writeData(Queue<AsyncContext> queue, String data) {
        if(queue != null) {
            Iterator<AsyncContext> iter = queue.iterator();
            while(iter.hasNext()) {
                AsyncContext asyncContext = iter.next();
                try {
                    ServletResponse response = asyncContext.getResponse();
                    PrintWriter out = response.getWriter();
                    out.write(data);
                    out.flush();
                    asyncContext.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void login(String username) {
        if (!usernameToAsyncContextMap.containsKey(username)) {
            StringBuilder data = new StringBuilder();
            data.append("{");
            data.append("\"type\" : \"login\"");
            data.append(",\"username\" : \"" + username + "\"");
            data.append("}");
            publish(null, username, data.toString());
        }
    }

    public void logout(String username) {
        if(username == null) {
            return;
        }
        Queue<AsyncContext> queue = usernameToAsyncContextMap.get(username);
        if(queue != null && queue.size() == 0) {
            StringBuilder data = new StringBuilder();
            data.append("{");
            data.append("\"type\" : \"logout\"");
            data.append(",\"username\" : \"" + username + "\"");
            data.append("}");
            publish(null, username, data.toString());
            usernameToAsyncContextMap.remove(username);
        }
    }

    public void send(String receiver, String sender, String msg) {
        StringBuilder data = new StringBuilder();
        data.append("{");
        data.append("\"type\" : \"msg\"");
        data.append(",\"username\" : \"" + sender + "\"");
        data.append(",\"msg\" : \"" + msg + "\"");
        data.append("}");
        publish(receiver, sender, data.toString());
    }

}
