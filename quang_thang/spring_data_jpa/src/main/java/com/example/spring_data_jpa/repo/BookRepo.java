package com.example.spring_data_jpa.repo;

import com.example.spring_data_jpa.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book, String> {

}
