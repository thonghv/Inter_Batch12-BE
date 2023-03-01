package com.example.spring_data_jpa.service;

import com.example.spring_data_jpa.entity.*;
import com.example.spring_data_jpa.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class BookService {
  @Autowired BookRepo repo;

  public Object findAll() {
    return repo.findAll();
  }

  public Book findOne(String id) {
    return repo.findById(id).get();
  }

  public boolean update(String bookId, String title) {
    if (!repo.existsById(bookId)) return false;

    Book updateBook = repo.findById(bookId).get();
    updateBook.setTitle(title);
    repo.save(updateBook);
    return true;
  }

  public void addOne(Book book) {
    repo.save(book);
  }

  public void deleteOne(String bookId) {
    repo.deleteById(bookId);
  }
}
