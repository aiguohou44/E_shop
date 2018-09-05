<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/28 0028
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/public/head.jsp" %>
    <style type="text/css">
        #menu {
            width:200px;
            /*border:1px solid red;*/
        }
        #menu ul {
            list-style: none;
            padding: 0px;
            margin: 0px;
        }
        #menu ul li {
            border-bottom: 1px solid #fff;

        }
        #menu ul li a {
            /*先将a标签转换为块级元素，才能设置宽和内间距*/
            display: block;
            background-color: #00a6ac;
            color: #fff;
            padding: 5px;
            text-decoration: none;
            text-align: center;
        }
        #menu ul li a:hover {
            background-color: #008792;
        }

    </style>

</head>
<body>
    <div id="menu">
        <ul>
            <li><a href="#">类别管理</a></li>
            <li><a href="#">商品管理</a></li>
        </ul>
    </div>
</body>
</html>
