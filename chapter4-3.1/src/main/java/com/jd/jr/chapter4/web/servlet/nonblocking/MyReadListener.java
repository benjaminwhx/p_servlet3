package com.jd.jr.chapter4.web.servlet.nonblocking;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午3:16
 */
public class MyReadListener implements ReadListener {

    private final ServletInputStream is;
    private final AsyncContext asyncContext;

    public MyReadListener(final ServletInputStream is, final AsyncContext asyncContext) {
        this.is = is;
        this.asyncContext = asyncContext;
    }

    @Override
    public void onDataAvailable() throws IOException {
        //当有可用数据时由容器发起回调（容器仅当isReady方法返回true时才调用，直到返回false）
        //is.isReady:如果有可以无阻塞获取的数据时(从false变为true时)，返回true，即当该方法返回true时去读取数据，肯定是不阻塞的

        System.out.println("服务器读取到数据:>>");
        int ch = -1;
        while(is.isReady() && ((ch = is.read()) != -1)) {
            System.out.write((char) ch);
        }
        System.out.println("<<等待..");
    }

    @Override
    public void onAllDataRead() throws IOException {
        //当所有数据都可用时回调（即读取完了所有数据）
        System.out.println("服务器数据读取完毕:" + is.isFinished());
        asyncContext.complete();//读取完毕后结束异步
    }

    @Override
    public void onError(final Throwable t) {
        //当处理请求出错时调用
        System.out.println("\n服务器读取失败了");
        t.printStackTrace(System.out);
        asyncContext.complete();//出错后结束异步
    }
}
