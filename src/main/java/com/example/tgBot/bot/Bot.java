package com.example.tgBot.bot;
import com.example.tgBot.city.CityApi;
import com.example.tgBot.entity.CityEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        CityEntity model = new CityEntity();
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message chat = update.getMessage();
            SendMessage msg = new SendMessage();
            msg.setChatId(chat.getChatId().toString());
            try {
                msg.setText(CityApi.getDecription(chat.getText(),model));
                execute(msg);
            } catch (IOException e) {
                msg.setText("No such city exists in the database. But you can add it using our Rest WebService!");
                execute(msg);
            }
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}