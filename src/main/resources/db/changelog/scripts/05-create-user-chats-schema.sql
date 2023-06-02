CREATE TABLE IF NOT EXISTS `users_chats` (
                               `chat_id` bigint DEFAULT NULL,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`user_id`),
                               CONSTRAINT `FKm9idubc8h2nd586vuvands3ti` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
