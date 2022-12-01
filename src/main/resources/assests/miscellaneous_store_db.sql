CREATE DATABASE IF NOT EXISTS miscellaneous_store_db;
USE miscellaneous_store_db;

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.administrator (
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(80) NOT NULL,
  middle_name VARCHAR(80),
  paternal_last_name VARCHAR(80) NOT NULL,
  maternal_last_name VARCHAR(80),
  identification_number VARCHAR(25) NOT NULL,
  identification_type VARCHAR(80) NOT NULL,
  date_of_birth DATE NOT NULL,
  date_of_hire DATE NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  residence_address VARCHAR(255) NOT NULL,
  role VARCHAR(80) NOT NULL,
  basic_salary BIGINT NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.inventory (
  id BIGINT AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.magazine_lot (
  isbn BINARY(16) NOT NULL,
  name VARCHAR(255) NOT NULL,
  publisher_name VARCHAR(255) NOT NULL,
  price_per_unit BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(isbn),
  CONSTRAINT fk_magazine_lot_inventory FOREIGN KEY (inventory_id)
    REFERENCES miscellaneous_store_db.inventory(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.book_lot (
  isbn BINARY(16) NOT NULL,
  name VARCHAR(255) NOT NULL,
  author_name VARCHAR(255) NOT NULL,
  publisher_name VARCHAR(255) NOT NULL,
  pages_number VARCHAR(255) NOT NULL,
  publication_date DATE NOT NULL,
  price_per_unit BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(isbn),
  CONSTRAINT fk_book_lot_inventory FOREIGN KEY (inventory_id)
    REFERENCES miscellaneous_store_db.inventory(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.disc_lot (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  publication_date DATE NOT NULL,
  disc_lot_format ENUM('DVD', 'BLUE_RAY') NOT NULL,
  execution_time_in_minutes INT NOT NULL,
  content_language VARCHAR(120) NOT NULL,
  content_classification VARCHAR(120) NOT NULL,
  price_per_unit BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_disc_lot_inventory FOREIGN KEY (inventory_id)
    REFERENCES miscellaneous_store_db.inventory(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.vinyl_record_lot (
  id BIGINT AUTO_INCREMENT,
  record_production_name VARCHAR(255) NOT NULL,
  artist_group_name VARCHAR(180) NOT NULL,
  publication_date DATE NOT NULL,
  musical_genre VARCHAR(180) NOT NULL,
  price_per_unit BIGINT NOT NULL,
  available_units INT NOT NULL,
  inventory_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_vinyl_record_lot_inventory FOREIGN KEY (inventory_id)
    REFERENCES miscellaneous_store_db.inventory(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.branch_office (
  id BIGINT AUTO_INCREMENT,
  administrator_id BIGINT,
  inventory_id BIGINT,
  PRIMARY KEY(id),
  CONSTRAINT fk_branch_office_inventory FOREIGN KEY (inventory_id)
    REFERENCES miscellaneous_store_db.inventory(id)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_branch_office_administrator FOREIGN KEY (administrator_id)
    REFERENCES miscellaneous_store_db.administrator(id)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.client (
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

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.branch_office_clients (
  client_id BIGINT NOT NULL,
  branch_office_id BIGINT NOT NULL,
  PRIMARY KEY(client_id, branch_office_id),
  CONSTRAINT fk_branch_office_clients_client FOREIGN KEY (client_id)
    REFERENCES miscellaneous_store_db.client(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_branch_office_clients_branch_office FOREIGN KEY (branch_office_id)
    REFERENCES miscellaneous_store_db.branch_office(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.worker (
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(80) NOT NULL,
  middle_name VARCHAR(80),
  paternal_last_name VARCHAR(80) NOT NULL,
  maternal_last_name VARCHAR(80),
  identification_number VARCHAR(25) NOT NULL,
  identification_type VARCHAR(80) NOT NULL,
  date_of_birth DATE NOT NULL,
  date_of_hire DATE NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  residence_address VARCHAR(255) NOT NULL,
  role VARCHAR(80) NOT NULL,
  basic_salary VARCHAR(255) NOT NULL,
  branch_office_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_worker_branch_office FOREIGN KEY (branch_office_id)
    REFERENCES miscellaneous_store_db.branch_office(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS miscellaneous_store_db.transaction (
  id BIGINT AUTO_INCREMENT,
  client_name VARCHAR(255) NOT NULL,
  client_identification_number VARCHAR(25) NOT NULL,
  client_identification_type VARCHAR(80) NOT NULL,
  product_name VARCHAR(255) NOT NULL,
  product_type ENUM('MAGAZINE', 'BOOK', 'DISC', 'VINYL_RECORD') NOT NULL,
  product_price BIGINT NOT NULL,
  product_quantity BIGINT NOT NULL,
  transaction_cost BIGINT NOT NULL,
  branch_office_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_transaction_branch_office FOREIGN KEY (branch_office_id)
    REFERENCES miscellaneous_store_db.branch_office(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);
