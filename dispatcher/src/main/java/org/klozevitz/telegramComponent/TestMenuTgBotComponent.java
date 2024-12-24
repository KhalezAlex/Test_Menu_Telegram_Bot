package org.klozevitz.telegramComponent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.controllers.UpdateController;
import org.klozevitz.enitites.appUsers.AppUser;
import org.klozevitz.repositories.appUsers.AppUserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

import static org.klozevitz.enitites.appUsers.enums.CompanyState.WAIT_FOR_PAYMENT_STATE;

@Log4j
@Component
@RequiredArgsConstructor
public class TestMenuTgBotComponent extends TelegramLongPollingBot {
    @Value(value = "${bot.username}")
    private String username;
    @Value(value = "${bot.token}")
    private String token;
    private final UpdateController updateController;
    private final AppUserRepo appUserRepo;

    @PostConstruct
    public void init() {
        updateController.registerBot(this);
    }

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
        updateController.processUpdate(update);
    }

    public void sendAnswerMessage(SendMessage sendMessage) {
        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }
}
