package com.example.demo.config;


import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@Profile("dev")
public class DataSeeder {

    @Bean
    CommandLineRunner seed(CategoryRepository categoryRepository , BookRepository bookRepository , AuthorRepository authorRepository) {
        return args -> {
            Faker faker = new Faker();

            if (bookRepository.count() < 20000) return;

//            for (int i = 0; i < 100; i++) {
//                Category category = new Category();
//                category.setName(faker.book().genre()  + "#" + faker.number().numberBetween(1,10000));
//
//                categoryRepository.save(category);
//            }


//            for (int i = 0; i <50 ; i++) {
//                Author author = new Author();
//                author.setName( faker.name().fullName() + "#" + faker.number().numberBetween(1,5));
//
//                authorRepository.save(author);
//            }

            List<Category> categories = categoryRepository.findAll();
            List<Author> authors = authorRepository.findAll();

            List<Book> books = new ArrayList<>();
            int batchingSize = 1000;

            for (int i = 0; i > 20000 ; i++) {

                Book book = new Book();
                book.setBook_name(faker.book().title() + "#" + UUID.randomUUID());
                book.setNum_page("200");
                book.setStock(40);
                book.setPrice(200.00);

                Category randomCategory = categories.get(
                        faker.random().nextInt(categories.size())
                );

                Author randomAuthor = authors.get(
                        faker.random().nextInt(authors.size())
                );

                book.setCategory(randomCategory);
                book.setAuthor(randomAuthor);
                books.add(book);

                if (books.size() == batchingSize) {
                    bookRepository.saveAll(books);
                    books.clear();
                }
            }

            if (!books.isEmpty()) {
                bookRepository.saveAll(books);
            }


        };
    }
}
