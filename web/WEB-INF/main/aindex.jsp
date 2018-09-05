<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/28 0028
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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


    <script type="text/javascript">

        $(function () {
            $("a[title]").click(function () {
                var text = $(this).text();
                var href = $(this).attr("title");
                //判断当前右边是否已有相应的tab
                if($("#tt").tabs("exists",text)){
                    $("#tt").tabs("select",text);
                }else {
                    //如果没有则创建一个新的tab，否则切换到当年tag
                    $("#tt").tabs("add",{
                            title:text,
                            //closable:true表示有关闭按钮
                            closable:true,
                            //content表示要显示的内容从哪获得,用<iframe>标签将一个页面的内容给包进来
                            content:'<iframe title=' + text + ' src=' +href + ' frameborder="0" width="100%" height="100%"/> '

                            //href:默认通过url地址加载远程的页面，但是仅仅是body部分

                    });
                }
            });
        });
    </script>
</head>

<body class="easyui-layout">
<%--north 顶部菜单 --%>
<div data-options="region:'north',title:'欢迎来到易购后台管理',split:true" style="height:100px;"></div>
<%-- 左侧菜单 --%>
<div data-options="region:'west',title:'系统操作',split:true" style="width:200px;">
    <!-- 此处显示的是系统菜单 -->
    <div id="menu" class="easyui-accordion" style="width:300px;height:200px;">
        <div title="基本操作" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
            <ul>
                <li><a href="#" title="send_category_query.action">类别管理</a></li>
                <li><a href="#" title="send_product_query.action">商品管理</a></li>
            </ul>

            <%--<h3 style="color:#0099FF;">Accordion for jQuery</h3>--%>
            <%--<p>Accordion is a part of easyui framework for jQuery.--%>
                <%--It lets you define your accordion component on web page more easily.</p>--%>
        </div>
        <div title="其他操作" data-options="iconCls:'icon-reload'" style="padding:10px;">
            <ul>
                <li><a href="#">类别管理</a></li>
                <li><a href="#">商品管理</a></li>
            </ul>
        </div>
        <%--<div title="Title3">content3</div>--%>
    </div>
</div>
<div data-options="region:'center',title:'后台操作页面'" style="padding:5px;background:#eee;">

    <div id="tt" class="easyui-tabs" data-options="fit:true">
        <div title="系统缺省页面" style="padding:10px;">
            此处以后显示相应的系统信息（当前操作系统的类型，当前项目的域名，硬件的相关配置或者显示报表
        </div>

    </div>

    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>

</div>
</body>

</html>
