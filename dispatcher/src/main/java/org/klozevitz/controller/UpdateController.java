package org.klozevitz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.telegramComponent.TestMenuTgBotComponent;
import org.klozevitz.utils.MessageUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Component
@RequiredArgsConstructor
public class UpdateController {
    private  final MessageUtils messageUtils;
    private TestMenuTgBotComponent bot;

    public void registerBot(TestMenuTgBotComponent bot) {
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Receiver update is null");
            return;
        }

        if (update.getMessage() != null) {
            distributeMessageByType(update);
        } else {
            var errorMessage = String.format("Received message type is unsupported %s", update);
            log.error(errorMessage);
        }
    }

    private void distributeMessageByType(Update update) {
        var message = update.getMessage();
        if (message.getText() != null) {
            processTextMessage(update);
        } else if (message.getDocument() != null) {
            processDocMessage(update);
        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        var message = "Unsupported message type!";
        var sendMessage = messageUtils.generateSendMessageWithText(update, message);
        setView(sendMessage);
    }

    private void setView(SendMessage sendMessage) {
        bot.sendAnswerMessage(sendMessage);
    }

    private void processTextMessage(Update update) {

    }

    private void processDocMessage(Update update) {
    }
}
