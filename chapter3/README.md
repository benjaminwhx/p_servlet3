### DispatcherServlet1
结论：dispatcher调用后，isAsyncStarted输出false。dispatch()方法会调用当前的路径，就是自己调自己。这样子可以组成一个循环链。

### DispatcherServlet2
结论：dispatch(String url)方法会调用对应的url地址。

### DispatcherServlet3
主要测试dispatch()的时候抛出异常如何处理，结论是如果AsyncContext增加了asyncListener的话，先执行onError()，再执行onComplete()。

### DispatcherServlet4
结论：AsyncContext没有增加asyncListener的话，dispatch()抛出异常会去找web.xml中的<error-page>的配置，跳转到ErrorServlet中处理，
它会抛出一个500的错误码：HttpServletResponse.SC_INTERNAL_SERVER_ERROR
它会把异常放在request的attribute中,key为RequestDispatcher.ERROR_EXCEPTION("javax.servlet.error.exception")
如果没有配置<error-page>或者没有找到，直接complete()

### DispatcherServlet5
结论：dispatch不会丢失之前的响应流，即保留这个响应流，接着在下一个servlet继续写入，这个不同于forward转发。所以会构成一个循环链一直输出。

### DispatcherServlet6
javax.servlet.async.request_uri
javax.servlet.async.context_path
javax.servlet.async.servlet_path
javax.servlet.async.path_info
javax.servlet.async.query_string
这些属性的值必须分别与HttpServletRequest的getRequestURI,、getContextPath、 getServletPath、getPathInfo、getQueryString
方法的返回值相等，这些方法在从客户端接收到的request对象上调用，值传递给调用链中的第一个servlet对象。
这些属性通过转发servlet的request对象的getAttribut方法访问。请注意，即使在多个转发和相继的包含（subsequent includes）被调用的情况下，这些属性必须始终反映原始请求中的信息。

isAsyncStarted()方法用来判断是否是当前线程是否启动异步模式，false表示没有启动异步模式，或者已经完成，否则请求被分发到其他线程处理