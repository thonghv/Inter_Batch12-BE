package com.example.spring_data_jpa.controller;

import com.example.spring_data_jpa.entity.Book;
import com.example.spring_data_jpa.service.BookService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The type Book controller. */
@RestController
@RequestMapping(value = {"/books", "/books/"})
public class BookController {

  /** The Book service. */
  @Autowired BookService bookService;

  /**
   * Gets all.
   *
   * @return the all book data
   */
  @GetMapping
  public Object getAll() {
    return bookService.findAll();
  }

  /**
   * Gets one.
   *
   * @param id the id from url path param
   * @return the one
   */
  @GetMapping("{id}")
  public ResponseEntity<Book> getOne(@PathVariable(value = "id") String id) {
    return ResponseEntity.ok().body(bookService.findOne(id));
  }

  /**
   * Create string.
   *
   * @param newBook the new book
   * @return the string
   */
  @PostMapping
  public String create(@RequestBody Book newBook) {
    try {
      bookService.add(newBook);
      return "Add Book Successfully";
    } catch (Exception e) {
      return "ERROR!!";
    }
  }

  /**
   * Update or add string.
   *
   * @param bookReq the book req
   * @return the string
   */
  @PutMapping(value = {"{id}", "{id}/"})
  public String update(@RequestBody Book bookReq) {
    if (bookService.update(bookReq)) {
      return "successfully";
    }
    return "ERROR";
  }

  /**
   * Update Some field of a record.
   *
   * @param id the id
   * @param fields the fields
   * @return the string
   */
  @PatchMapping("{id}")
  public String update(@PathVariable("id") String id, @RequestBody Map<String, Object> fields) {
    if (bookService.updateFields(id, fields)) {
      return "successfully";
    }
    return "ERROR";
  }
}
