package com.carsharing.repository;

import com.carsharing.model.UserChat;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    Optional<UserChat> findByUserId(Long userId);
}
