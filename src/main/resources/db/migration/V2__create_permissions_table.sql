CREATE TABLE permissions (
     id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(25) NOT NULL UNIQUE,
     role_id INT NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk_permissions_role
         FOREIGN KEY (role_id)
             REFERENCES roles(id)
);