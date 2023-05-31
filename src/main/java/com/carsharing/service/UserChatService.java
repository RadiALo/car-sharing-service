package com.carsharing.service;

import com.carsharing.model.User;
import com.carsharing.model.UserChat;
import java.util.Optional;

public interface UserChatService {
    UserChat save(UserChat userChat);

    Optional<UserChat> findByUser(User user);
}
