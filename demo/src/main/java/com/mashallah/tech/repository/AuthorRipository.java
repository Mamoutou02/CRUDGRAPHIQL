package com.mashallah.tech.repository;

import com.mashallah.tech.entite.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRipository extends JpaRepository<Author, Long> {
}
