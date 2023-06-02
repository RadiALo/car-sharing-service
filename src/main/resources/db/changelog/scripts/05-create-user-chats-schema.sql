CREATE TABLE IF NOT EXISTS `users_chats` (
                               `chat_id` bigint DEFAULT NULL,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`user_id`)
);

