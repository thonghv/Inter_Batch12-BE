package com.example.demoo.repository;

import com.example.demoo.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepo {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> getAll(){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT * FROM Product";
        Query<Product> query = session.createNativeQuery(queryStr, Product.class);
        session.close();
        return query.getResultList();
    }
}
