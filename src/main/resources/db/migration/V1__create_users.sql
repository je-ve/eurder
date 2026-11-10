CREATE TABLE users (
                       id VARCHAR(255) PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(100),
                       role VARCHAR(50) NOT NULL,
                       address_street_name VARCHAR(255),
                       address_house_number VARCHAR(50),
                       address_postal_code VARCHAR(50),
                       address_city VARCHAR(255)
);