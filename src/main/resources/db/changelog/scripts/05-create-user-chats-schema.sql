CREATE TABLE IF NOT EXISTS users_chats (
                                         id BIGINT PRIMARY KEY NOT NULL,
                                         user_id BIGINT NOT NULL,
                                         chat_id BIGINT,
                                         FOREIGN KEY (user_id) REFERENCES users (id)
    );