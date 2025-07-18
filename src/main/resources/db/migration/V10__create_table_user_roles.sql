CREATE TABLE user_roles(
    id_user BIGINT,
    id_roles BIGINT,
    PRIMARY KEY(id_user, id_roles),
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_roles) REFERENCES roles(id)
);