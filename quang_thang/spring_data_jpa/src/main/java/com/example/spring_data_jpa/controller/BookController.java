package com.example.spring_data_jpa.controller;

import com.example.spring_data_jpa.entity.*;
import com.example.spring_data_jpa.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/books", "/books/"})
public class BookController {

  @Autowired BookService bookService;

  @GetMapping
  public Object getAll() {
    return bookService.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<Book> getOne(@PathVariable("id") String id) {
    return ResponseEntity.ok().body(bookService.findOne(id));
  }

  @PostMapping
  public String create(@RequestBody Book newBook) {
    try {
      bookService.addOne(newBook);
      return "Add Book Successfully";
    } catch (Exception e) {
      return "ERROR!!";
    }
  }

  @PutMapping(value = {"{id}", "{id}/"})
  public String update(@PathVariable("id") String id, @RequestParam("title") String title) {
    if (bookService.update(id, title)) return "successfully";
    return "ERROR";
  }
}
