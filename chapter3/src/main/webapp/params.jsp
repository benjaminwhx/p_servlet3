
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
      <form action="${pageContext.request.contextPath}/params" method="post">
          <input type="text" name="name"> <br/>
          <input type="submit" value="submit">
      </form>
</body>
</html>