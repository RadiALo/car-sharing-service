package com.carsharing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "users_chats")
public class UserChat {
    @Id
<<<<<<< HEAD
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "chat_id")
=======
    private Long userId;
>>>>>>> main
    private Long chatId;
}
