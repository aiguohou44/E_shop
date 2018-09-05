package com.action;

import com.model.FileImage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.AccountService;
import com.service.CategoryService;
import com.service.ProductService;
import com.utils.FileUpload;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,RequestAware, SessionAware, ApplicationAware {

    //page和rows和分页有关，pageMap存放查询的数据，然后打包成json格式用的
    //page和rows实现get和set方法，pageMap只需要实现get方法即可，因为pageMap不是接收前台参数的，是让struts2要返回给前台的
    protected Integer page;
    protected Integer rows;
    protected Map<String, Object> pageMap = null;//让不同的Action自己去实现

    //获取要删除的ids，要有get和set方法
    //流是用来向前台返回数据的，这个数据是让struts获取的，然后通过流的形式传到前台，所以实现get方法即可
    protected String ids;
    protected InputStream inputStream;

    //用来装有将要被打包成json格式返回给前台的数据，下面要实现get方法
    protected List<T> jsonList = null;

    protected T model;//设置一个私有成员变量接收url带过来的参数

    //封装了图片信息的类
    protected FileImage fileImage;

    //上传文件工具类
    @Resource
    protected FileUpload fileUpload;


    @Resource
    protected CategoryService categoryService;//设置categoryService是为了很直观的看出与Spring整合前后的不同
    @Resource
    protected AccountService accountService;
    @Resource
    protected ProductService productService;






    @Override
    public T getModel() {//这里通过解析传进来的T来new一个对应的instance

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class) type.getActualTypeArguments()[0];

        try {
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public FileImage getFileImage() {
        return fileImage;
    }

    public void setFileImage(FileImage fileImage) {
        this.fileImage = fileImage;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    protected  Map<String, Object> request;
    protected  Map<String, Object> session;
    protected  Map<String, Object> application;

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    @Override
    public void setRequest(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setSession(Map<String, Object> request) {
        this.request = request;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Map<String, Object> getPageMap() {
        return pageMap;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public List<T> getJsonList() {
        return jsonList;
    }
}
