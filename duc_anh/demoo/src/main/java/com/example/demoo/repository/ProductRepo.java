package com.example.demoo.repository;

import com.example.demoo.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepo {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> getAll(){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT * FROM Product";
        Query<Product> query = session.createNativeQuery(queryStr, Product.class);
        List<Product> result = null;
        result = query.getResultList();
        session.close();
        return result;
    }

    public void add(Product product){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "INSERT INTO Product(name, price) VALUES('"+ product.getName() +"', "+ product.getPrice() +");";
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("kkkkkkkkllllllllllllllllllllll");
    }

    public void update(String id, String name, Double price){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product();
        String queryStr = "UPDATE Product SET name='"+name+"',price='"+price+"' WHERE id= " + id;
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();
    }

    public void delete(String id){
        System.out.println("delete");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "DELETE FROM Product WHERE id = "+id;
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();
    }
}
