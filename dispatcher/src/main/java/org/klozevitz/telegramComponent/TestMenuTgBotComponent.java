package org.klozevitz.telegramComponent;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TestMenuTgBotComponent extends TelegramLongPollingBot {
    @Value(value = "${bot.username}")
    private String username;
    @Value(value = "${bot.token}")
    private String token;
    private static final Logger log = Logger.getLogger(TestMenuTgBotComponent.class);

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var textFromMessage = message.getText();
        log.debug(textFromMessage);
//        System.out.println(textFromMessage);
    }
}
