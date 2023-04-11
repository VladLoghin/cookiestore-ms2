USE `cookiestore-db`;

create table if not exists inventories(
                                          id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                          invId VARCHAR(36),
    stock_type VARCHAR (100)
    );

create table if not exists Cookie(
                                     id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     cookie_id VARCHAR(36),
    invId VARCHAR(36),
    cookie_name VARCHAR (10),
    quantity int,
    cookie_size VARCHAR(20),
    price Decimal(5,2)
    );
