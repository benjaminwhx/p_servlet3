# servlet3-example
This is my example about servlet3 feature.

如果web.xml 描述符中的metadata-complete元素设置为true，则在class文件和绑定在jar包中的web-fragments中的注解将不被处理。这意味着，所有应用的元数据通过web.xml描述符指定。

多个fragment.xml时,name一定不能重复,否则会被覆盖

ServletContainerInitializer初始化器:
http://blog.csdn.net/wangyangzhizhou/article/details/52013779

关于servlet中返回的Transfer-Encoding: chunked是什么意思这里有详细说明:
https://imququ.com/post/transfer-encoding-header-in-http.html#simple_thread
## chapter4-resource
[http://www.blogjava.net/yongboy/archive/2011/01/03/346208.html](http://www.blogjava.net/yongboy/archive/2011/01/03/346208.html)