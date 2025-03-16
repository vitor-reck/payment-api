CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE emails_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE accounts_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE emails(
	id BIGINT PRIMARY KEY DEFAULT nextval('emails_seq'),
	address VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE accounts(
	id BIGINT PRIMARY KEY DEFAULT nextval('accounts_seq'),
	balance DECIMAL NOT NULL
);

CREATE TABLE customers(
	id BIGINT PRIMARY KEY DEFAULT nextval('users_seq'),
	full_name VARCHAR(255) NOT NULL,
	cpf VARCHAR(14) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	fk_email INT UNIQUE NOT NULL,
	fk_account INT UNIQUE NOT NULL,
	CONSTRAINT fk_customers_email FOREIGN KEY (fk_email) REFERENCES emails(id) ON DELETE CASCADE,
	CONSTRAINT fk_customers_account FOREIGN KEY (fk_account) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE shopkeepers(
	id BIGINT PRIMARY KEY DEFAULT nextval('users_seq'),
	full_name VARCHAR(255) NOT NULL,
	cnpj VARCHAR(18) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	fk_email INT UNIQUE NOT NULL,
	fk_account INT UNIQUE NOT NULL,
	CONSTRAINT fk_shopkeepers_email FOREIGN KEY (fk_email) REFERENCES emails(id) ON DELETE CASCADE,
	CONSTRAINT fk_shopkeepers_account FOREIGN KEY (fk_account) REFERENCES accounts(id) ON DELETE CASCADE
);