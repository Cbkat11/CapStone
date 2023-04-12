BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE restaurant (
    restaurant_id SERIAL,
    name varchar(100) NOT NULL,
    type varchar(100) NOT NULL,
    address varchar(200) NOT NULL,
    hours varchar(200) NOT NULL,
    phone_number varchar(20),
    stars numeric,
    img_url varchar(600),
    takeout boolean,
    delivery boolean,
    CONSTRAINT PK_restaurant PRIMARY KEY (restaurant_id)
);

COMMIT TRANSACTION;
