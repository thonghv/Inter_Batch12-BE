package com.quockiet.demoCrud.responsitory;


import com.quockiet.demoCrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsitory extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}