CREATE TABLE IF NOT EXISTS payments (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        type VARCHAR(255) NOT NULL,
                        rental_id BIGINT,
                        session_url VARCHAR(255) NOT NULL,
                        session_id VARCHAR(255) NOT NULL,
                        amount DECIMAL(10,2) NOT NULL,
                        FOREIGN KEY (rental_id) REFERENCES rentals(id)
    );