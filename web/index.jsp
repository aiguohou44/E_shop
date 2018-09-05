<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27 0027
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  <body>
  <!-- 下面两种写法都可以访问 --></span>
  <%--<a href="${pageContext.request.contextPath }/category_update.action">访问update</a>--%>
  <a href="${pageContext.request.contextPath }/category_update.action?id=5&type=aawd&hot=56&aid=2">访问update</a>

  <a href="category_save.action?id=5&type=haha&hot=1">测试ModelDriven</a>


  <a href="category_save.action">访问save</a>
  <a href="category_query.action">查询所有类别</a><br/>

  <c:forEach items="${requestScope.categoryList}" var="item">
    ${item.id } | ${item.type } | ${item.hot } <br/>
  </c:forEach>
  <c:forEach items="${sessionScope.categoryList }" var="category">
    ${category.id } | ${category.type } | ${category.hot } <br/>
  </c:forEach>
  <c:forEach items="${applicationScope.categoryList }" var="category">
    ${category.id } | ${category.type } | ${category.hot } <br/>
  </c:forEach>

  <hr>
  <a href="send_main_aindex.action">测试到后台</a>
  </body>
</html>
