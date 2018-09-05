package com.service.impl;

import com.service.BaseService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T>{


    private Class clazz; //clazz中存储了当前操作的类型，即泛型T

    @Resource //spring 使用反射注入
    private SessionFactory sessionFactory;

    public BaseServiceImpl() {
        //下面三个打印信息可以去掉，这里是给自己看的

        System.out.println("this代表的是当前调用构造方法的对象" + this);
        System.out.println("获取当前this对象的父类信息" + this.getClass().getSuperclass());
        System.out.println("获取当前this对象的父类信息(包括泛型信息)" + this.getClass().getGenericSuperclass());
        //拿到泛型的参数类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class)type.getActualTypeArguments()[0];

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession(){
        //从当前线程获取session，如果没有则创建一个新的session
        return  sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void delete(int id) {
        System.out.println(clazz.getSimpleName());
        String hql = "delete" + clazz.getSimpleName() + " as c where c.id=:id";
        getSession().createQuery(hql)
                        .setInteger("id",id)
                        .executeUpdate();
    }

    @Override
    public T get(int id) {
        return (T) getSession().get(clazz,id);
    }

    @Override
    public List<T> query() {
        String hql = "from " + clazz.getSimpleName();//注意 from 后的空格，必须要有、不然结果是from类名

        return getSession().createQuery(hql).list();
    }
}
