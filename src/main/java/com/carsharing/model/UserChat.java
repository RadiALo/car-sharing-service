package com.carsharing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_chat")
public class UserChat {
    @Id
    private Long userId;
    private Long chatId;
}
