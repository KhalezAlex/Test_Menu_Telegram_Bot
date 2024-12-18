package org.klozevitz.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.UpdateProducer;
import org.klozevitz.telegramComponent.TestMenuTgBotComponent;
import org.klozevitz.utils.MessageUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.RabbitQueue.DOC_MESSAGE_UPDATE;
import static org.klozevitz.RabbitQueue.TEXT_MESSAGE_UPDATE;

@Log4j
@Component
@RequiredArgsConstructor
public class UpdateController {
    private final MessageUtils messageUtils;
    private final UpdateProducer updateProducer;
    private TestMenuTgBotComponent bot;

    public void registerBot(TestMenuTgBotComponent bot) {
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Receiver update is null");
            return;
        }

        if (update.hasMessage() || update.hasCallbackQuery()) {
            distributeMessageByType(update);
        } else {
            var errorMessage = String.format("Received message type is unsupported: %s", update);
            log.error(errorMessage);
        }
    }

    private void distributeMessageByType(Update update) {
        var message = update.getMessage();
        if (message.hasText() || update.hasCallbackQuery()) {
            processTextMessage(update);
        } else if (message.hasDocument()) {
            processDocMessage(update);
        } else {
            setUnsupportedMessageTypeView(update);
        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        var message = "Unsupported message type!";
        var sendMessage = messageUtils.generateSendMessageWithText(update, message);
        setView(sendMessage);
    }

    public void setView(SendMessage sendMessage) {
        bot.sendAnswerMessage(sendMessage);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(TEXT_MESSAGE_UPDATE, update);
    }

    private void processDocMessage(Update update) {
        updateProducer.produce(DOC_MESSAGE_UPDATE, update);
        setFileReceivedMessage(update);
    }

    private void setFileReceivedMessage(Update update) {
        var message = "Document received! Processing";
        var sendMessage = messageUtils.generateSendMessageWithText(update, message);
        setView(sendMessage);
    }
}
