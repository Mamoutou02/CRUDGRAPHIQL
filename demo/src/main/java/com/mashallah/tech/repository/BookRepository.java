package com.mashallah.tech.repository;

import com.mashallah.tech.entite.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
