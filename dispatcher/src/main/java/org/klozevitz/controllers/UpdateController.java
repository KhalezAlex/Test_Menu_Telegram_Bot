package org.klozevitz.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.UpdateProducer;
import org.klozevitz.telegramComponent.TestMenuTgBotComponent;
import org.klozevitz.utils.MessageUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.RabbitQueue.*;

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
        if (update.hasCallbackQuery()) {
            processUpdateWithCallbackQuery(update);
        } else if (update.hasMessage()) {
            processUpdateWithMessage(update);
        } else {
            setUnsupportedMessageTypeView(update);
        }
    }

    private void processUpdateWithMessage(Update update) {
        var message = update.getMessage();
        if (message.hasText()) {
            if (message.getText().startsWith("/")) {
                processCommandMessage(update);
            } else {
                processTextMessage(update);
            }
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
        updateProducer.produce(TEXT_UPDATE, update);
    }


    private void processCommandMessage(Update update) {
        updateProducer.produce(COMMAND_UPDATE, update);
    }

    private void processUpdateWithCallbackQuery(Update update) {
        updateProducer.produce(CALLBACK_QUERY_UPDATE, update);
    }

    private void processDocMessage(Update update) {
        updateProducer.produce(DOC_UPDATE, update);
        setFileReceivedMessage(update);
    }

    private void setFileReceivedMessage(Update update) {
        var message = "Document received! Processing";
        var sendMessage = messageUtils.generateSendMessageWithText(update, message);
        setView(sendMessage);
    }
}
