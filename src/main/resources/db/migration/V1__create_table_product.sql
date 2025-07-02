CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(75) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) CHECK (price > 0) NOT NULL,
    quantity INTEGER DEFAULT 0,
    category VARCHAR(50),
    active BOOLEAN DEFAULT true
);