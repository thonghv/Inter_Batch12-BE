package com.example.spring_data_jpa.service;

import com.example.spring_data_jpa.entity.Book;
import com.example.spring_data_jpa.repo.BookRepo;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class BookService {
  @Autowired BookRepo repo;

  public Object findAll() {
    return repo.findAll();
  }

  public Book findOne(String id) {
    return repo.findById(id).get();
  }

  public void add(Book book) {
    repo.save(book);
  }

  public boolean update(Book newBook) {
    if (!repo.existsById(newBook.getId())) {
      return false;
    }
    Book updateBook = repo.findById(newBook.getId()).get();
    repo.save(updateBook);
    return true;
  }

  public boolean updateFields(String bookId, Map<String, Object> fields) {
    Optional<Book> updateBook = repo.findById(bookId);
    if (!updateBook.isPresent()) {
      return false;
    }
    fields.forEach(
        (key, value) -> {
          Field field = ReflectionUtils.findField(Book.class, key);
          field.setAccessible(true);
          ReflectionUtils.setField(field, updateBook.get(), value);
        });
    return true;
  }

  public void deleteOne(String bookId) {
    repo.deleteById(bookId);
  }

  public void deleteAll() {
    repo.deleteAll();
  }
}
