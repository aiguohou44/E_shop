package com.service;

import com.model.Category;

import java.util.List;

//CategoryService接口继承BaseService接口
public interface CategoryService extends BaseService<Category> {


    /*
     * 只要添加CategoryService本身需要的新的方法即可，公共方法已经在BaseService中了
     */

    //查询类别信息，级联管理员
    public List<Category> queryJoinAccount(String type,int page,int size); //使用类别的名称查询
    //根据关键字查询总记录数
    public Long getCount(String type);
    //根据ids删除多条记录
    public void deleteByIds(String ids);
}
