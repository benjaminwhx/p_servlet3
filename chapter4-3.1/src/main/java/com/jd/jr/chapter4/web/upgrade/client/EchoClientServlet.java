package com.jd.jr.chapter4.web.upgrade.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 需要走socket，因为HttpURLConnection的实现是半双工的，即必须Output后才能Input。
 *
 * 客户端没有使用非阻塞I/O
 *
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午5:52
 */
@WebServlet(name = "echoClientServlet", urlPatterns = "/echo", asyncSupported = true)
public class EchoClientServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Connection", "Keep-Alive");
        resp.addHeader("Cache-Control", "private");
        resp.addHeader("Pragma", "no-cache");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("正在交互...");
        resp.getWriter().flush();
        resp.flushBuffer();

        Socket socket = new Socket(InetAddress.getByName("localhost"), req.getLocalPort());
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write(("POST " + req.getContextPath() + "/upgrade HTTP/1.1\r\n").getBytes("ISO-8859-1"));
        os.write(("Connection: Upgrade\r\n").getBytes("ISO-8859-1"));
        os.write(("Upgrade: echo\r\n").getBytes("ISO-8859-1"));
        os.write(("\r\n").getBytes("ISO-8859-1"));
        os.flush();

        StringBuilder buffer = new StringBuilder();
        int ch = -1;
        while((ch = is.read()) != -1) {
            buffer.append((char) ch);
            int bufferLength = buffer.length();
            //如果请求到 \r\n\r\n 表示头信息读完了
            if(bufferLength > 4 &&
                    buffer.charAt(bufferLength - 4) == '\r' && buffer.charAt(bufferLength - 3) == '\n' &&
                    buffer.charAt(bufferLength - 2) == '\r' && buffer.charAt(bufferLength - 1) == '\n') {
                //状态行 和 响应头接收完毕
                System.out.println(buffer.toString());
                break;
            }
        }

        //处理响应的状态行 和 响应头
        String[] data = buffer.toString().split("\r\n");
        //HTTP/1.1 101 Web Socket Protocol Handshake
        String statusLine = data[0];
        int statusCode = Integer.valueOf(statusLine.substring(9, 12));
        String statusMessage = statusLine.substring(13);
        String connectionHeader = null;
        String upgradeHeader = null;
        String protocolHeader = null;

        for(int i = 1; i < data.length; i++) {
            String[] header = data[i].split(":");
            if("Connection".equalsIgnoreCase(header[0])) {
                connectionHeader = header[1].trim();
            }
            if("Upgrade".equalsIgnoreCase(header[0])) {
                upgradeHeader = header[1].trim();
            }
            if("protocol".equalsIgnoreCase(header[0])) {
                protocolHeader = header[1].trim();
            }
        }

        if(statusCode != 101) {
            System.out.print("客户端:切换协议失败了,响应状态码:" + statusCode + " " + statusMessage);
        } else {//切换协议可能成功

            if(!"Upgrade".equalsIgnoreCase(connectionHeader)) {
                System.out.println("客户端:服务器返回了错误的Connection头,应该为:Upgrade,实际是:" + connectionHeader);
            } else if(!"echo".equalsIgnoreCase(upgradeHeader)) {
                System.out.println("客户端:服务器返回了错误的Upgrade头,应该为:echo,实际是:" + connectionHeader);
            } else {
                System.out.println("客户端:切换协议成功了,服务器支持的协议:" + protocolHeader);
                //协议切换成功
                buffer = new StringBuilder();
                for(int i = 1; i <= 10; i++) {
                    //1、写数据
                    os.write(("hello " + i + "\r\n").getBytes("ISO-8859-1"));
                    os.flush();
                    //2、读数据
                    while(true) {
                        ch = is.read();
                        buffer.append((char)ch);
                        int bufferLength = buffer.length();
                        //如果接收到的是CRLF，表示一次读取完毕
                        if(bufferLength >= 2 &&
                                buffer.charAt(bufferLength - 2) == '\r' && buffer.charAt(bufferLength - 1) == '\n') {
                            System.out.println("客户端接收到回显:" + buffer);
                            buffer = new StringBuilder();
                            break;
                        }
                    }
                }
                //客户端告诉服务器通信结束
                os.write("BYE\r\n".getBytes("ISO-8859-1"));
                os.flush();
                System.out.println("客户端结束通信");

            }
        }

        os.close();
        is.close();

        resp.getWriter().write("交互完成...");
        resp.getWriter().flush();
        resp.flushBuffer();
    }
}
