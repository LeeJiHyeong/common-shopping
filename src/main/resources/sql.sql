CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nick_name VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (10) NOT NULL
);

CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR (50) ,
    seller BIGINT,
    price INTEGER,
    category BIGINT ,
    FOREIGN KEY(seller) REFERENCES user(id),
    FOREIGN KEY(category) REFERENCES category(id)
);

CREATE TABLE sell_post (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    writer BIGINT,
    product BIGINT,
    name VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE review (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    writer BIGINT,
    content TEXT,
    image VARCHAR(255),
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    sell_post BIGINT NOT NULL,
    FOREIGN KEY (sell_post) REFERENCES sell_post(id) ON delete cascade
);



