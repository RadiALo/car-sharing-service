CREATE TABLE IF NOT EXISTS cars (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                      model VARCHAR(255) NOT NULL,
                      brand VARCHAR(255) NOT NULL,
                      type VARCHAR(255) NOT NULL,
                      inventory INT NOT NULL CHECK (inventory > 0),
                      daily_fee DECIMAL(10,2) NOT NULL
);
