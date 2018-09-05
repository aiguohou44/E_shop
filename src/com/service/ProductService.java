package com.service;

import com.model.Account;
import com.model.Product;

import java.util.List;

//ProductService接口继承BaseService接口
public interface ProductService extends BaseService<Product> {


    /*
     * 只要添加ProductService本身需要的新的方法即可，公共方法已经在BaseService中了
     */

    //查询商品信息，级联类别
    public List<Product> queryJoinProduct(String name, int page, int size); //使用商品的名称查询
    //根据关键字查询总记录数
    public Long getCount(String name);
    //根据ids删除多条记录
    public void deleteByIds(String ids);
}
