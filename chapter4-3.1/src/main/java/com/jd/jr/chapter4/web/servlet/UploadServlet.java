package com.jd.jr.chapter4.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午2:46
 */
@MultipartConfig(
        location = "", //即默认为 javax.servlet.context.tempdir 如mvn jetty:run 在chapter4\target\tmp中
        maxRequestSize = 1024 * 1024 * 2,   //指定一次请求最大的上传数据量（上传的总和） 单位：字节， -1表示不限制
        maxFileSize = 1024 * 1024 * 1, //设定单个文件的最大大小，-1表示不限制
        fileSizeThreshold = 1024 * 1024 * 10 //当上传的文件大小大于该值时才写入文件
)
@WebServlet(name = "uploadServlet", urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        //访问 /upload.jsp
        System.out.println(req.getParameter("name"));

        try {
            Part part = req.getPart("file1");
            // file元素的name
            System.out.println(part.getName());
            //servlet 3.1 可以直接调用getSubmittedFileName得到客户端提交时的文件名
            System.out.println(part.getSubmittedFileName());
        } catch (Exception ise) {
            //文件上传失败
            ise.printStackTrace();
            String errorMsg = ise.getMessage();
            if(errorMsg.contains("Request exceeds maxRequestSize")) {
                //所有上传的部分超出了整个上传大小
                System.out.println("所有上传的部分超出了整个上传大小");
            } else if(errorMsg.contains("Multipart Mime part file1 exceeds max filesize")) {
                //某个文件 超出单个文件上传大小
                System.out.println("某个文件 超出单个文件上传大小");
            } else {
                //其他错误
                System.out.println("其他错误");
            }
        }

    }


}
