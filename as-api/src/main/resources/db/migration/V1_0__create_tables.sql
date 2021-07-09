CREATE TABLE IF NOT EXISTS user (
    user_id serial PRIMARY KEY,
    username varchar(16) UNIQUE NOT NULL,
    password varchar(100) NOT NULL,
    email varchar(255) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP NOT NULL,
);

CREATE TABLE IF NOT EXISTS account (
    account_id serial PRIMARY KEY,
    user_id integer NOT NULL,
    name varchar(20) NOT NULL,
    balance money NOT NULL,
    deposit money NOT NULL,
    realized_gains money NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_updated TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (user_id)
);

CREATE TABLE stock (
    stock_id serial PRIMARY KEY,
    name varchar(50) NOT NULL,
    ticker varchar(10) NOT NULL,
    exchange varchar(40) NOT NULL,
    price money NOT NULL,
    last_updated_price TIMESTAMP NOT NULL
);

CREATE TABLE account_holding (
    account_id integer NOT NULL,
    stock_id integer NOT NULL,
    amount integer NOT NULL,
    gav money NOT NULL,
    PRIMARY KEY (account_id, stock_id),
    FOREIGN KEY (account_id)
        REFERENCES account (account_id),
    FOREIGN KEY (stock_id)
        REFERENCES stock (stock_id)
);