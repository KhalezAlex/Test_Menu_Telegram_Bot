package org.klozevitz.telegramComponent;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j
@Component
public class TestMenuTgBotComponent extends TelegramLongPollingBot {
    @Value(value = "${bot.username}")
    private String username;
    @Value(value = "${bot.token}")
    private String token;

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

        var responseText = String.format("ответ на сообщение \"%s\"", textFromMessage);

        var response = new SendMessage();
        response.setChatId(message.getChatId().toString());
        response.setText(responseText);
        sendAnswerMessage(response);
    }

    private void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }
}
