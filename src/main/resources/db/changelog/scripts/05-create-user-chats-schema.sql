CREATE TABLE IF NOT EXISTS `users_chats` (
                               `chat_id` bigint DEFAULT NULL,
                               `id` bigint NOT NULL,
                               `user_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`)
);

