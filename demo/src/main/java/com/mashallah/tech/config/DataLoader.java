package com.mashallah.tech.config;

import com.mashallah.tech.entite.Author;
import com.mashallah.tech.entite.Book;
import com.mashallah.tech.repository.AuthorRipository;
import com.mashallah.tech.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final AuthorRipository authorRipository;
    private final BookRepository bookRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {

            // Auteur 1
            Author author1 = new Author();
            author1.setFirstName("Victor");
            author1.setLastName("Hugo");

            authorRipository.save(author1);

            // Livre 1
            Book book1 = new Book();
            book1.setTitle("Les Misérables");
            book1.setPageCount(1200);
            book1.setAuthor(author1);

            // Livre 2
            Book book2 = new Book();
            book2.setTitle("Notre-Dame de Paris");
            book2.setPageCount(940);
            book2.setAuthor(author1);

            bookRepository.save(book1);
            bookRepository.save(book2);

            // Auteur 2
            Author author2 = new Author();
            author2.setFirstName("Paulo");
            author2.setLastName("Coelho");

            authorRipository.save(author2);

            // Livre 3
            Book book3 = new Book();
            book3.setTitle("L'Alchimiste");
            book3.setPageCount(250);
            book3.setAuthor(author2);

            bookRepository.save(book3);

            System.out.println("Données initiales chargées !");
        };
    }
}