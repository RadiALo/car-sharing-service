package com.carsharing.service.impl;

import com.carsharing.bot.CarSharingBot;
import com.carsharing.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramNotificationService implements NotificationService {
    private final CarSharingBot carSharingBot;

    @Override
    public void sendNotification(String rental) {
        SendMessage sendMessage = new SendMessage();

        try {
            carSharingBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
