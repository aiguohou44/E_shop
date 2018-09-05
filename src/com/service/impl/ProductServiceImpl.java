package com.service.impl;

import com.model.Account;
import com.model.Product;
import com.service.AccountService;
import com.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO（模块自身的业务逻辑）
 * @author lkx
 *
 */

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {



    public List<Product> queryJoinProduct(String name, int page, int size) {
        String hql = "from Product p left join fetch p.category c left join fetch c.account  " +
                "where p.name like :name order by p.id";

        return getSession().createQuery(hql)
                .setString("name","%"+ name +"%")
                .setFirstResult((page -1)*size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public Long getCount(String name) {
        String hql = "select count(p) from Product p where p.name like :name";
        return (Long) getSession().createQuery(hql)
                .setString("name", "%" + name + "%")
                .uniqueResult(); //返回一条记录:总记录数

    }

    public void deleteByIds(String ids) {
        String hql = "delete from Product c where c.id in (" + ids + ")";
        getSession().createQuery(hql).executeUpdate();
    }





}
