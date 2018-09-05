package com.service.impl;

import com.model.Category;
import com.service.CategoryService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @Description TODO（模块自身的业务逻辑）
 * @author lkx
 *
 */

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {


    /*
     * 只需实现CategoryService接口中新增的方法即可，公共方法已经在BaseServiceImpl中实现了
     */
    @Override
    public List<Category> queryJoinAccount(String type,int page,int size) {

//        String hql = "from Category c where c.type like :type";
        String hql = "from Category c left join fetch c.account where c.type like :type";

        return getSession().createQuery(hql)
                        .setString("type","%"+type+"%")
                        .setFirstResult((page-1)*size)//从第几个开始显示
                        .setMaxResults(size)//显示几个
                        .list();
    }

    @Override
    public Long getCount(String type) {

        String hql = "select count(c) from Category c  where c.type like :type";

        return (Long) getSession().createQuery(hql)
                .setString("type","%"+type+"%")
                .uniqueResult();//返回一条记录:总记录数
    }

    @Override
    public void deleteByIds(String ids) {
        String hql = "delete from Category c where c.id in (" + ids + ")";
        getSession().createQuery(hql).executeUpdate();
    }

}
