package com.example.demoo.controller;

import com.example.demoo.model.Product;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControler {
  private ArrayList<Product> listProducts = new ArrayList<Product>();

  public ProductControler(){
    System.out.println("vvvvvvvvvvvvvvvvvvvvvvv");
    listProducts.add(new Product("1", "Pen",500.0));
  }

  @GetMapping("/product/{idProduct}")
  public Product get(@PathVariable String idProduct){
    Product result = null;
    for (Product product : listProducts) {
      if(product.getId().equals(idProduct)){
        result = product;
        break;
      }
    }

    return result;
  }

  @PostMapping("/add")
  public void add(String id, String name, Double price){
    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setPrice(price);
    listProducts.add(product);
  }

  @DeleteMapping("/remove/{idProduct}")
  public void remove(@PathVariable String idProduct){
    for (Product product : listProducts) {
      if(product.getId().equals(idProduct)){
        listProducts.remove(product);
        break;
      }
    }
  }
}
