CREATE TABLE IF NOT EXISTS rentals (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        rental_date TIMESTAMP NOT NULL,
                        return_date TIMESTAMP NOT NULL,
                        actual_return_date TIMESTAMP,
                        car_id BIGINT,
                        user_id BIGINT,
                        is_active BIT,
                        FOREIGN KEY (car_id) REFERENCES cars(id),
                        FOREIGN KEY (user_id) REFERENCES users(id)
    );
