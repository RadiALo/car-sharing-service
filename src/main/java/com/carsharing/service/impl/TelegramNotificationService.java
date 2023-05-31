package com.carsharing.service.impl;

import com.carsharing.bot.CarSharingBot;
import com.carsharing.model.Rental;
import com.carsharing.model.UserChat;
import com.carsharing.service.NotificationService;
import com.carsharing.service.UserChatService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramNotificationService implements NotificationService {
    private final CarSharingBot carSharingBot;
    private final UserChatService userChatService;

    @Override
    public void sendNotification(Rental rental) {
        SendMessage sendMessage = new SendMessage();
        Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
        if (userChat.isPresent()) {
            sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
            sendMessage.setText(rental.getUser().getFirstName() + ", you have rent car "
                    + rental.getCar().getModel() + "! Your daily fee:"
                    + rental.getCar().getDailyFee() + ". You need to rturn car until "
                    + rental.getActualReturnDate().toString() + ".");
            try {
                carSharingBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
