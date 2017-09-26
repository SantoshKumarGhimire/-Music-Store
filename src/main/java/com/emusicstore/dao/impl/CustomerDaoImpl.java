package com.emusicstore.dao.impl;

import com.emusicstore.dao.CustomerDao;
import com.emusicstore.model.Authorities;
import com.emusicstore.model.Cart;
import com.emusicstore.model.Customer;
import com.emusicstore.model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addCustomer(Customer customer) {

        Session session = sessionFactory.getCurrentSession();

        customer.getBillingAddress().setCustomer(customer);
        customer.getShippingAddress().setCustomer(customer);

        session.saveOrUpdate(customer);
        session.saveOrUpdate(customer.getBillingAddress());
        session.saveOrUpdate(customer.getShippingAddress());

        Users users = new Users();
        Authorities authorities = new Authorities();
        users.setCustomerId(customer.getCustomerId());
        users.setEnabled(true);
        users.setUsername(customer.getUsername());
        users.setPassword(customer.getPassword());

        authorities.setAuthority("ROLE_USER");
        authorities.setUsername(customer.getUsername());

        session.saveOrUpdate(users);
        session.saveOrUpdate(authorities);

        Cart cart = new Cart();

        cart.setCustomer(customer);

        customer.setCart(cart);

        session.saveOrUpdate(cart);
        session.saveOrUpdate(customer);
        session.flush();



    }

    @Override
    @SuppressWarnings("JPA")
    public Customer getCustomerById(int customerId) {
        Session session = sessionFactory.getCurrentSession();
       Customer customer=(Customer) session.get(Customer.class,customerId);
        return customer;
    }

    @Override
    @SuppressWarnings("JpaQlInspection")
    public List<Customer> getAllCustomer() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Customer");

        return query.list() ;
    }
}
