CREATE DATABASE IF NOT EXISTS store_db;
USE store_db;

CREATE TABLE IF NOT EXISTS store_db.administrator (
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(80) NOT NULL,
  middle_name VARCHAR(80),
  paternal_last_name VARCHAR(80) NOT NULL,
  maternal_last_name VARCHAR(80),
  identification_number VARCHAR(25) NOT NULL,
  identification_type VARCHAR(80) NOT NULL,
  residence_address VARCHAR(80) NOT NULL,
  date_of_birth DATE NOT NULL,
  date_of_hire DATE NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  role VARCHAR(80) NOT NULL,
  basic_salary BIGINT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS store_db.inventory (
  id BIGINT AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS store_db.magazine (
  isbn VARCHAR(13),
  name VARCHAR(255) NOT NULL,
  publisher_name VARCHAR(255) NOT NULL,
  price BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(isbn),
  CONSTRAINT fk_magazine_inventory FOREIGN KEY (inventory_id)
    REFERENCES store_db.inventory(id)
);

CREATE TABLE IF NOT EXISTS store_db.book (
  isbn VARCHAR(13),
  author_name VARCHAR(255) NOT NULL,
  publisher_name VARCHAR(255) NOT NULL,
  pages_number INT NOT NULL,
  publication_date DATE NOT NULL,
  price BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(isbn),
  CONSTRAINT fk_book_inventory FOREIGN KEY (inventory_id)
    REFERENCES store_db.inventory(id)
);

CREATE TABLE IF NOT EXISTS store_db.disc (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  publication_date DATE NOT NULL,
  disc_format ENUM('DVD', 'Blue-Ray') NOT NULL,
  execution_time INT NOT NULL,
  content_language VARCHAR(255) NOT NULL,
  content_classification VARCHAR(25) NOT NULL,
  price BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_disc_inventory FOREIGN KEY (inventory_id)
    REFERENCES store_db.inventory(id)
);

CREATE TABLE IF NOT EXISTS store_db.vinyl_record (
  id BIGINT AUTO_INCREMENT,
  artist_group_name VARCHAR(255) NOT NULL,
  record_production_name VARCHAR(255) NOT NULL,
  publication_date DATE NOT NULL,
  musical_genre VARCHAR(255) NOT NULL,
  price BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_vinyl_record_inventory FOREIGN KEY (inventory_id)
    REFERENCES store_db.inventory(id)
);

CREATE TABLE IF NOT EXISTS store_db.branch_office (
  id BIGINT AUTO_INCREMENT,
  inventory_id BIGINT NOT NULL,
  administrator_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_branch_office_inventory FOREIGN KEY (inventory_id)
    REFERENCES store_db.inventory(id),
  CONSTRAINT fk_branch_office_administrator FOREIGN KEY (administrator_id)
    REFERENCES store_db.administrator(id)
);

CREATE TABLE IF NOT EXISTS store_db.client (
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(80) NOT NULL,
  middle_name VARCHAR(80),
  paternal_last_name VARCHAR(80) NOT NULL,
  maternal_last_name VARCHAR(80),
  identification_number VARCHAR(25) NOT NULL,
  identification_type VARCHAR(80) NOT NULL,
  date_of_birth DATE NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  purchases_number INT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS store_db.branch_office_clients (
  client_id BIGINT NOT NULL,
  branch_office_id BIGINT NOT NULL,
  PRIMARY KEY(client_id, branch_office_id),
  CONSTRAINT fk_branch_office_clients_client FOREIGN KEY (client_id)
    REFERENCES store_db.client(id),
  CONSTRAINT fk_branch_office_clients_branch_office FOREIGN KEY (branch_office_id)
    REFERENCES store_db.branch_office(id)
);

CREATE TABLE IF NOT EXISTS store_db.employee (
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(80) NOT NULL,
  middle_name VARCHAR(80),
  paternal_last_name VARCHAR(80) NOT NULL,
  maternal_last_name VARCHAR(80),
  identification_number VARCHAR(25) NOT NULL,
  identification_type VARCHAR(80) NOT NULL,
  residence_address VARCHAR(80) NOT NULL,
  date_of_birth DATE NOT NULL,
  date_of_hire DATE NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  role VARCHAR(80) NOT NULL,
  basic_salary VARCHAR(255) NOT NULL,
  branch_office_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_employee_branch_office FOREIGN KEY (branch_office_id)
    REFERENCES store_db.branch_office(id)
);

CREATE TABLE IF NOT EXISTS store_db.transaction (
  id BIGINT AUTO_INCREMENT,
  client_identification_number VARCHAR(25) NOT NULL,
  client_identification_type VARCHAR(80) NOT NULL,
  product_name VARCHAR(255) NOT NULL,
  product_type VARCHAR(15) NOT NULL,
  product_price BIGINT NOT NULL,
  branch_office_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_trasaction_branch_office FOREIGN KEY (branch_office_id)
    REFERENCES store_db.branch_office(id)
);
