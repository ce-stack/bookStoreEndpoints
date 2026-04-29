CREATE TABLE books (
       id INT NOT NULL AUTO_INCREMENT,
       book_name VARCHAR(25) NOT NULL UNIQUE,
       num_page VARCHAR(25) NOT NULL,

       author_id INT NOT NULL,
       category_id INT NOT NULL,
       PRIMARY KEY (id) ,
       CONSTRAINT fk_books_author FOREIGN KEY (author_id)
           REFERENCES authors(id),

       CONSTRAINT fk_books_category FOREIGN KEY (category_id)
           REFERENCES categories(id)
);