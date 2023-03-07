package com.example.demoo.controller;

import com.example.demoo.model.Product;
import java.util.ArrayList;
import java.util.List;

import com.example.demoo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductControler {
  private ArrayList<Product> listProducts = new ArrayList<Product>();
  @Autowired
  private ProductRepo productRepo;

  public ProductControler(){
    System.out.println("aaaaaaaaaaaaaaa");
    listProducts.add(new Product("1", "Pen",500.0));
    listProducts.add(new Product("2", "Book",1000.0));
  }
  //lay product theo id
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
  //lay danh sach product
  @GetMapping("/product/all")
  public List<Product> getListProducts(){
    return productRepo.getAll();
  }
  //
  @PostMapping("/product/add")
  public void add(String name, Double price){
    System.out.println(name + " " + price);
    productRepo.add(new Product(name, price));
  }

  @PutMapping("/product/update/{idProduct}")
  public void update(@PathVariable String idProduct, String name, Double price){
    System.out.println(name + " " + price);
    productRepo.update(idProduct, name, price);
  }

  @DeleteMapping("product/remove/{idProduct}")
  public void remove(@PathVariable String idProduct){
    productRepo.delete(idProduct);
    }
  }
