<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/28 0028
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/public/head.jsp" %></head>
    <style type="text/css">
        form div {
            margin:5px;
        }
    </style>
    <script type="text/javascript">

        $(function () {
            //iframe中的datagrid对象

            var dg = parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg");


            //对管理员的下拉列表框进行远程加载
            $("#cc").combobox({
                //将请求发送给accountAction中的query方法处理，这里需要将处理好的数据返回到这边来显示了 ，所以后台需要将数据打包成json格式发过来
                url:'account_query.action',
                valueField:'id',
                textField:'login', //我们下拉列表中显示的是管理员的登录名
                panelHeight:'auto', //自适应高度
                panelWidth:120,//下拉列表是两个组件组成的
                width:120, //要同时设置两个宽度才行
                editable:false //下拉框不允许编辑

            });

            // 完成数据的回显，更新时，用户肯定先选择了要更新的那一行，首先我们得拿到那一行
            var rows = dg.datagrid("getSelections");
            //将拿到的那一行对应的数据字段加载到表单里，实现回显
            $("#ff").form('load',{

                id:rows[0].id,
                type:rows[0].type,
                hot:rows[0].hot,
                'account.id':rows[0].account.id
            });

            //回显完了数据后，设置一下验证功能

            $("input[name=type]").validatebox({ //这里是“类别名称”的验证功能，如果用户没填好就提交的话，会有提示
                required:true,
                missingMessage:'请输入类别名称' //提示的内容
            });

            //窗体弹出默认是禁用验证，因为刚弹出的窗口，用户还没填就显示的话，太丑
            $('#ff').form("disableValidation");
            //注册button的事件。即当用户点击“添加”的时候做的事
            $("#btn").click(function () {
                //开启验证
                $("#ff").form("enableValidation");
                //如果验证成功，则提交数据
                if($('#ff').form("validate")){
                    //调用submit方法提交数据
                    $('#ff').form("submit",{
                        url: 'category_update.action', //将请求提交给categoryAction中的save方法处理
                        success: function(){ //成功后
                            //如果成功了，关闭当前窗口
                            parent.$("#win").window("close");
                            dg.datagrid("reload");
                        }
                    });
                }
            });


        });
    </script>

<body>
    <form id="ff" method="post">
        <div>
            <%--for 属性规定 label 与哪个表单元素绑定。--%>
            <label >商品名称:</label> <input type="text" name="type" />
        </div>
        <div>

            <label >所属管理员：</label>
            <!-- 基于Html代码的方式
            <select id="cc" class="easyui-combobox" name="dept" style="width:200px;">
                <option value="aa">aitem1</option>
                <option>bitem2</option>
                <option>bitem3</option>
                <option>ditem4</option>
                <option>eitem5</option>
            </select>
             -->
            <input id="cc" name="account.id"/>
        </div>
        <div>
            <label >热点:</label>
            是<input type="radio" name="hot" value="1" />&nbsp;
            否 <input type="radio" name="hot" value="34" />
        </div>
        <div>
            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">更新</a>
            <%-- 更新 需要的id--%>
            <input type="hidden" name="id" />
        </div>
    </form>

</body>
</html>
