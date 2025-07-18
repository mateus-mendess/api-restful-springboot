ALTER TABLE users
ADD CONSTRAINT fk_user_roles
FOREIGN KEY (id_roles) REFERENCES roles(id);