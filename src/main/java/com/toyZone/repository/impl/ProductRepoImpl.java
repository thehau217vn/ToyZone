package com.toyZone.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.toyZone.model.Product;
import com.toyZone.repository.ProductRepo;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Repository
public class ProductRepoImpl extends AbstractRepoImpl<Integer, Product> implements ProductRepo {

    @Override
    public List<Product> getListSpKhuyenMai(int offset, int limit) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        String hql = "FROM Product WHERE  discount > 0 ORDER BY updated_at DESC";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(limit);
        return (List<Product>) query.list();
    }

    @Override
    public int getCountSpKhuyenMai() {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        String hql = "FROM Product WHERE  discount > 0";
        Query query = session.createQuery(hql);
        return ((List<Product>) query.list()).size();
    }
}
