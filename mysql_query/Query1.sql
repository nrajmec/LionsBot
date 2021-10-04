DROP TABLE IF EXISTS mytestdb.customers;
DROP TABLE IF EXISTS mytestdb.orders;
DROP TABLE IF EXISTS mytestdb.address;
DROP TABLE IF EXISTS mytestdb.shipments;
DROP TABLE IF EXISTS mytestdb.roles;
DROP TABLE IF EXISTS mytestdb.users_roles;
DROP TABLE IF EXISTS mytestdb.customers_orders;
DROP TABLE IF EXISTS mytestdb.orders_shipments;

CREATE TABLE mytestdb.customers (  
    customer_id int NOT NULL AUTO_INCREMENT,  
    customer_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password varchar(255) NOT NULL,
    contact_no varchar(15) NOT NULL,
    PRIMARY KEY(customer_id)
);

CREATE TABLE mytestdb.orders (  
	order_id int NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,  
    order_date DATE NOT NULL,
    total_price DOUBLE NOT NULL,
    num_items int NOT NULL,
    primary key(order_id),
    FOREIGN KEY (customer_id) REFERENCES mytestdb.customers(customer_id)
);

CREATE TABLE mytestdb.address (  
	address_id int NOT NULL auto_increment,
    customer_id int NOT NULL, 
    address1 varchar(150) NOT NULL,
    address2 varchar(150),
    postal_code int not null,
    country varchar(50) not null,
    PRIMARY KEY(address_id),
    FOREIGN KEY (customer_id) REFERENCES mytestdb.customers(customer_id)
);

CREATE TABLE mytestdb.shipments (  
	shipment_id int NOT NULL auto_increment,
    order_id int NOT NULL, 
    shipment_date date NOT NULL,
    method BINARY(16) NOT NULL,
    PRIMARY KEY(shipment_id),
    FOREIGN KEY (order_id) REFERENCES mytestdb.orders(order_id)
);

-- Insert into customers table
INSERT INTO mytestdb.customers 
(customer_name, email, password, contact_no) VALUES 
('user1', 'admin@gmail.com', 'user2', '34867'),
('user2', 'naveen@gmail.com', 'user2', '34867'),
('test', 'test@gmail.com', 'test', '34867'), 
('user1', 'admin@gmail.com', 'user2', '34867'),
('user2', 'naveen@gmail.com', 'user2', '34867'),
('test', 'test@gmail.com', 'test', '34867');
SELECT * FROM mytestdb.customers;

-- Insert into orders table
INSERT INTO mytestdb.orders(customer_id, order_date, total_price, num_items) 
VALUES (1, CURDATE(), 1000, 10),(2, CURDATE(), 100, 10),(2, CURDATE(), 50, 5);
SELECT * FROM mytestdb.orders;

-- Insert into address table    
INSERT INTO mytestdb.address (customer_id, address1, address2, postal_code, country) 
VALUES (1, 'West street', 'NY', 40001, 'US'), (3, 'Langford street', 'CA', 40001, 'US');

SELECT * FROM mytestdb.address;

-- Insert into shipment table
INSERT INTO mytestdb.shipments (order_id, shipment_date, method) 
VALUES (1, curdate(), UUID_TO_BIN(UUID())), (2, curdate(), UUID_TO_BIN(UUID()));

SELECT * FROM mytestdb.shipments;