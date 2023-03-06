package com.example.spring_data_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

@Entity
/** The type Book. */
public class Book {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String title;
  private List<String> author;
  private int year;

  /** Instantiates a new Book. */
  public Book() {}

  /**
   * Instantiates a new Book.
   *
   * @param title the title
   * @param author the author
   * @param year the year
   */
  public Book(String title, List<String> author, int year) {
    this.title = title;
    this.author = author;
    this.year = year;
  }

  /**
   * Gets author.
   *
   * @return the author
   */
  public List<String> getAuthor() {
    return author;
  }

  /**
   * Sets author.
   *
   * @param author the author
   */
  public void setAuthor(List<String> author) {
    this.author = author;
  }

  /**
   * Gets year.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets year.
   *
   * @param year the year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }
}
