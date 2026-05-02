CREATE TABLE user_books (
    id INT NOT NULL AUTO_INCREMENT ,
    user_id INT NOT NULL ,
    book_id INT NOT NULL ,
    PRIMARY KEY (id) ,

    CONSTRAINT fk_user_books_user FOREIGN KEY (user_id)
                        REFERENCES users(id),

    CONSTRAINT fk_user_books_book FOREIGN KEY (book_id)
                        REFERENCES books(id)
);