CREATE TABLE users (
       id INT NOT NULL AUTO_INCREMENT,
       full_name VARCHAR(25) NOT NULL,
       email VARCHAR(100) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL,
       role_id INT NOT NULL,

       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,

       PRIMARY KEY (id),

       CONSTRAINT fk_users_role
           FOREIGN KEY (role_id)
               REFERENCES roles(id)
);