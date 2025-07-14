CREATE TABLE user_roles(
    id_user SERIAL NOT NULL,
    id_roles SERIAL NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_roles) REFERENCES roles(id)
);