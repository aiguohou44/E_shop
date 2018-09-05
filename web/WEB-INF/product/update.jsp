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
            margin:10px;
        }
    </style>
    <script type="text/javascript">

        $(function () {
            //iframe中的datagrid对象

            var dg = parent.$("iframe[title='商品管理']").get(0).contentWindow.$("#dg");


            //对管理员的下拉列表框进行远程加载
            $("#cc").combobox({
                //将请求发送给accountAction中的query方法处理，这里需要将处理好的数据返回到这边来显示了 ，所以后台需要将数据打包成json格式发过来
                url:'category_query.action',
                valueField:'id',
                textField:'type', //我们下拉列表中显示的是管理员的登录名
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
                name:rows[0].name,
                price:rows[0].price,
                remark:rows[0].remark,
                xremark:rows[0].xremark,
                commend:rows[0].commend,
                open:rows[0].open,
                'category.id':rows[0].category.id
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
                        url: 'product_update.action', //将请求提交给categoryAction中的save方法处理
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
            <label >商品名称:</label> <input type="text" name="name" />
        </div>
        <div>
            <label >商品价格:</label> <input type="text" name="price" />
        </div>
        <div>
            <label >简单描述:</label> <input type="text" name="remark" />
        </div>
        <div>
            <label >详细描述:</label> <input type="text" name="xremark" />
        </div>
        <div>
            <label >加入推荐:</label>
            推荐<input type="radio" name="commend" value="true" />&nbsp;
            不推荐 <input type="radio" name="commend" value="false" />
        </div>
        <div>
            <label >是否有效:</label>
            上架<input type="radio" name="open" value="true" />&nbsp;
            下架<input type="radio" name="open" value="false" />
        </div>
        <div>
            <label>图片上传:</label> <input type="file" name="fileImage.upload" />
        </div>
        <div>
            <label >所属类别：</label>
            <input id="cc" name="category.id"/>
        </div>
        <div>
            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-update'">更新</a>
            <a id="reset" href="#" class="easyui-linkbutton">重 置</a>
        </div>
        <div>
            <%-- 更新 需要的id--%>
            <input type="hidden" name="id" />
        </div>
    </form>

</body>
</html>
