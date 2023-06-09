package com.carsharing.service.impl;

import com.carsharing.bot.CarSharingBot;
import com.carsharing.exception.NotificationException;
import com.carsharing.model.Rental;
import com.carsharing.model.UserChat;
import com.carsharing.service.NotificationService;
import com.carsharing.service.RentalService;
import com.carsharing.service.UserChatService;
import java.time.LocalDate;
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
    public void sendNotificationAboutPossibleRental(Rental rental) {
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
                throw new NotificationException("Can`t send notification about possible rental "
                        + "to user with chat id: "
                        + userChat.get().getChatId(), e);
            }
        }
    }

    @Override
    public void sendNotificationAboutImpossibleRental(Rental rental) {
        SendMessage sendMessage = new SendMessage();
        Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
        if (userChat.isPresent()) {
            sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
            String message = String.format("Sorry! All car of this model: "
                    + "%s has already occupied.\n"
                    + "Please select free car.", rental.getCar().getModel());
            sendMessage.setText(message);
            try {
                carSharingBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new NotificationException("Can`t send notification about impossible rental "
                        + "to user with chat id: "
                        + userChat.get().getChatId(), e);
            }
        }
    }

    @Scheduled(cron = "${cron.expression}")
    @Override
    public void sentNotificationAboutFailedDeadLine() {
        List<Rental> rentals = rentalService.findAll();
        for (Rental rental : rentals) {
            if (LocalDate.now().isAfter(rental.getRentalDate())) {
                Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
                String message = String.format("Hi! Your rental date is failed "
                                + "rental date: %s "
                                + "today's date: %s",
                        rental.getRentalDate().toString(), LocalDate.now());
                sendMessage.setText(message);
                try {
                    carSharingBot.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new NotificationException("Can`t send notification about failed deadline "
                            + "to user with chat id: "
                            + userChat.get().getChatId(), e);
                }
            }
        }
    }

    @Override
    public void sentNotificationAboutReturnedCar(Rental rental) {
        Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
        if (userChat.isPresent()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
            String message = "Car: " + rental.getCar().getModel() + " has been returned!";
            sendMessage.setText(message);
            try {
                carSharingBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new NotificationException("Can`t send notification about returned car "
                        + "to user with chat id: "
                        + userChat.get().getChatId(), e);
            }
        }
    }

    @Override
    public void sentNotificationAboutSuccessfulPayment(Rental rental) {
        Optional<UserChat> userChat = userChatService.findByUser(rental.getUser());
        if (userChat.isPresent()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(userChat.get().getChatId()));
            String message = "Your payment was successful!";
            sendMessage.setText(message);
            try {
                carSharingBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new NotificationException("Can`t send notification about successful payment"
                        + " to user with chat id: "
                        + userChat.get().getChatId(), e);
            }
        }
    }
}
