package com.emusicstore.dao.impl;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();

    }

    @Override
    public void editProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();

    }

    @Override
    public Product getProductById(int productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class,productId);
        session.flush();
        return product;
    }

    @Override
    @SuppressWarnings("JpaQlInspection")
    public List<Product> getProductList() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        List<Product> products = query.list();
        return products;
    }

    @Override
    public void deleteProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();

        session.delete(product);

        session.flush();

    }
}