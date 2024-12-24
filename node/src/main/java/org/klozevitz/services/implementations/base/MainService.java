package org.klozevitz.services.implementations.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.enitites.appUsers.AppUser;
import org.klozevitz.model.entities.RawData;
import org.klozevitz.model.repositories.RawDataRepo;
import org.klozevitz.repositories.appUsers.AppUserRepo;
import org.klozevitz.services.interfaces.base.AnswerProducer;
import org.klozevitz.services.interfaces.base.Main;
import org.klozevitz.services.interfaces.producersByMessageType.CallbackQueryMessageAnswerProducer;
import org.klozevitz.services.interfaces.producersByMessageType.CommandMessageAnswerProducer;
import org.klozevitz.services.interfaces.producersByMessageType.DocumentMessageAnswerProducer;
import org.klozevitz.services.interfaces.producersByMessageType.TextMessageAnswerProducer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Log4j
@Service
@RequiredArgsConstructor
public class MainService implements Main {
    private final RawDataRepo rawDataRepo;
    private final AppUserRepo appUserRepo;
    private final CallbackQueryMessageAnswerProducer callbackQueryMessageAnswerProducer;
    private final TextMessageAnswerProducer textMessageAnswerProducer;
    private final CommandMessageAnswerProducer commandMessageAnswerProducer;
    private final DocumentMessageAnswerProducer documentMessageAnswerProducer;

    @Override
    public void processCallbackQueryMessage(Update update) {
        log.debug("processing callback message");

        saveRawData(update);

        findOrSaveAppUser(update);

        callbackQueryMessageAnswerProducer.produce(update);
    }

    @Override
    public void processTextMessage(Update update) {
        log.debug("processing text message");

        saveRawData(update);

        findOrSaveAppUser(update);

        textMessageAnswerProducer.produce(update);
    }

    @Override
    public void processCommandMessage(Update update) {
        log.debug("processing command message");

        saveRawData(update);

        findOrSaveAppUser(update);

        commandMessageAnswerProducer.produce(update);
    }

    @Override
    public void processDocMessage(Update update) {
        log.debug("processing document message");

        saveRawData(update);

        findOrSaveAppUser(update);

        documentMessageAnswerProducer.produce(update);
    }

    private AppUser findOrSaveAppUser(Update update) {
        User telegramUser = update.hasMessage() ?
                update.getMessage().getFrom() :
                update.getCallbackQuery().getFrom();

        AppUser persistentAppUser = appUserRepo.findAppUserByTelegramUserId(telegramUser.getId());
        if (persistentAppUser == null) {
            AppUser transientAppUser = AppUser.builder()
                    .telegramUserId(telegramUser.getId())
                    .username(telegramUser.getUserName())
                    .isActive(true)
                    .build();
            return appUserRepo.save(transientAppUser);
        }
        return persistentAppUser;
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                .event(update)
                .build();
        rawDataRepo.save(rawData);
    }
}
