package com.carsharing.repository;

import com.carsharing.model.User;
import com.carsharing.model.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    Optional<UserChat> findByUser(User user);
}
