package com.jd.jr.chapter4.web.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 上午9:55
 */
@MultipartConfig(

        fileSizeThreshold = 10000 //当上传的文件大小大于该值时才写入文件
)
@WebServlet(name = "uploadServlet3", urlPatterns = "/upload3")
public class UploadServlet3 extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file1");
        InputStream is = part.getInputStream();
        System.out.println(IOUtils.toString(is));
        is.close();

        /**
         * jetty为例：
         * 此处的fileSizeThreshold没有作用，因为：
         * 1、如果content-disposition头中包含文件名，那么一定会创建一个新的
         * 2、否则创建一个byte array input stream
         * 3、如果[2]的大小超过fileSizeThreshold也会创建
         *
         * 4、当请求销毁时，会自动删除那些临时文件
         * 4.1、如果@MultipartConfig指定了location，那么是非临时的
         * 4.2、如果Part.write 那么也是非临时的
         *
         */

    }

}
