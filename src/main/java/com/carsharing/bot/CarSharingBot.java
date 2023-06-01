package com.carsharing.bot;

import com.carsharing.config.BotConfig;
import com.carsharing.model.User;
import com.carsharing.model.UserChat;
import com.carsharing.service.UserChatService;
import com.carsharing.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class CarSharingBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UserService userService;
    private final UserChatService userChatService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    Optional<User> user = userService.findByEmail(messageText);
                    if (user.isPresent()) {
                        UserChat userChat = new UserChat();
                        //userChat.setUser(user.get());
                        userChat.setUserId(user.get().getId());
                        userChat.setChatId(chatId);
                        userChatService.save(userChat);
                    } else {
                        String answer = "Unfortunately, there is no user with such email "
                                + messageText;
                        sendMessage(chatId, answer);
                    }
            }
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ". Nice to meet you!" + System.lineSeparator()
                + "Please enter your email";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can't send message " + textToSend);
        }
    }
}
