package com.carsharing.service.impl;

import com.carsharing.model.User;
import com.carsharing.model.UserChat;
import com.carsharing.repository.UserChatRepository;
import com.carsharing.service.UserChatService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserChatServiceImpl implements UserChatService {
    private final UserChatRepository userChatRepository;

    @Override
    public UserChat save(UserChat userChat) {
        return userChatRepository.save(userChat);
    }

    @Override
    public Optional<UserChat> findByUser(User user) {
        return userChatRepository.findByUser(user);
    }
}
