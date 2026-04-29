CREATE TABLE ratings(
    id INT NOT NULL AUTO_INCREMENT ,
    value VARCHAR(25) NOT NULL ,
    user_id INT NOT NULL ,
    book_id INT NOT NULL ,
    PRIMARY KEY (id) ,

    CONSTRAINT fk_ratings_user FOREIGN KEY (user_id)
                    REFERENCES users(id) ,

    CONSTRAINT fk_ratings_book FOREIGN KEY (book_id)
                    REFERENCES books(id)
);