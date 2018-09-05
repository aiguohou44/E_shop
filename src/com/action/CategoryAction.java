package com.action;

import com.model.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CategoryService;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category>  {




    public String queryJoinAccount(){

        //用来存储分页的数据
        pageMap = new HashMap<String, Object>();

        //根据关键字和分页的参数查询相应的数据。这个方法我们在Service中写过了，当时完成级联查询
        List<Category> categoryList = categoryService.queryJoinAccount(model.getType(), page, rows);
        pageMap.put("rows", categoryList); //存储为JSON格式，从上一节的json文件可以看出，一个key是total,一个key是rows，这里先把rows存放好
        //根据关键字查询总记录数
        Long total = categoryService.getCount(model.getType()); //这个方法没写，我们等会儿去Service层完善一下
        pageMap.put("total", total); //存储为JSON格式，再把total存放好
        return "jsonMap";

    }

    public  String deleteByIds(){

        System.out.println(ids);

        categoryService.deleteByIds(ids);
        //如果删除成功就会往下执行，我们将"true"以流的形式传给前台

        inputStream = new ByteArrayInputStream("true".getBytes());//将"true"的字节存到流inputStream中
        return "stream";
    }


    public  void update(){
        System.out.println("-------update--------");
        System.out.println(categoryService);//整合前后输出不同
        categoryService.update(model); //新加一条语句，来更新数据库

    }
    public  void save(){
        System.out.println("-------save--------");
        System.out.println(categoryService);
        System.out.println(model);
        categoryService.save(model);
    }

    /*
    *此处 不知道是否有线程不安全的问题，
    * jsonList 被多个类继承使用 ，虽然有 原型的作用域限制
    *
    * */
    public String query(){
        //解决方案一，采用相应的map取代原来的内置对象，这样与jsp没有依赖，但是代码量比较大

//        ActionContext.getContext().put("categoryList",categoryService.query());//放到request域中
//        ActionContext.getContext().getSession().put("categoryList",categoryService.query());//放到session域中
//        ActionContext.getContext().getApplication().put("categoryList",categoryService.query());//放到application域中

        //解决方案二，实现相应的接口(RequestAware,SessionAware,ApplicationAware)，让相应的map注入
        request.put("categoryList", categoryService.query());
        session.put("categoryList", categoryService.query());
        application.put("categoryList", categoryService.query());
        jsonList = categoryService.query();

        return "jsonList";
    }


}
