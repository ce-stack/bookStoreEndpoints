CREATE TABLE comments(
    id INT NOT NULL AUTO_INCREMENT ,
    comment_value VARCHAR(255) NOT NULL ,
    user_id INT NOT NULL,
    book_id INT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,

    PRIMARY KEY (id) ,

    CONSTRAINT fk_comments_user FOREIGN KEY(user_id)
                     REFERENCES users(id) ,

    CONSTRAINT fk_comments_book FOREIGN KEY (book_id)
                     REFERENCES books(id)
);