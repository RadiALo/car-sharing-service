package com.carsharing.service.impl;

import com.carsharing.bot.CarSharingBot;
import com.carsharing.model.Rental;
import com.carsharing.model.UserChat;
import com.carsharing.service.NotificationService;
import com.carsharing.service.RentalService;
import com.carsharing.service.UserChatService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramNotificationService implements NotificationService {
    private final CarSharingBot carSharingBot;
    private final UserChatService userChatService;
    private final RentalService rentalService;

    @Override
    public void sendCreationNotification(Rental rental) {
        SendMessage sendMessage = new SendMessage();
        Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
        if (userChat.isPresent()) {
            sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
            sendMessage.setText(rental.getUser().getFirstName() + ", you have rent car "
                    + rental.getCar().getModel() + "!\nYour daily fee: "
                    + rental.getCar().getDailyFee() + ".\nRental date: "
                    + rental.getRentalDate().toString() + ".\nYou need to return car until: "
                    + rental.getReturnDate().toString() + ".");
            try {
                carSharingBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void sendReturnConformation(Rental rental) {
        SendMessage sendMessage = new SendMessage();
        Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
        if (userChat.isPresent()) {
            sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
            sendMessage.setText(rental.getUser().getFirstName() + ", you have successfully returned car. "
            + "Thank you for using our service!");
            try {
                carSharingBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
