package com.service;

import com.model.Account;
import com.model.Category;

import java.util.List;

//AccountService接口继承BaseService接口
public interface AccountService extends BaseService<Account> {


    /*
     * 只要添加AccountService本身需要的新的方法即可，公共方法已经在BaseService中了
     */

    public List<Account> query();


}
