package com.model;

import com.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        BaseAction baseAction = new BaseAction();

//        baseAction.getCategory();
        List<Account> list = new ArrayList<Account>();

        list.add(new Account("1","123","213"));
        list.add(new Account("1","1234","213"));
        list.add(new Account("1","12345","213"));

//      重写对象的equals 方法 使用集合的contains方法可以判断 它们是否重复(根据某个属性比如身份证号)
         boolean bool = list.contains(new Account("23","123","34"));
        System.out.println( bool);
    }

}
