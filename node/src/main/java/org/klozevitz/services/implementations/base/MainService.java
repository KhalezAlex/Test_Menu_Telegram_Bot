package org.klozevitz.services.implementations.base;

import lombok.RequiredArgsConstructor;
import org.klozevitz.enitites.appUsers.AppUser;
import org.klozevitz.model.entities.RawData;
import org.klozevitz.model.repositories.RawDataRepo;
import org.klozevitz.repositories.appUsers.AppUserRepo;
import org.klozevitz.services.interfaces.base.AnswerProducer;
import org.klozevitz.services.interfaces.base.Main;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class MainService implements Main {
    private final RawDataRepo rawDataRepo;
    private final AnswerProducer answerProducer;
    private final AppUserRepo appUserRepo;

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);
        
        var appUser = findOrSaveAppUser(update);

        var userState = appUser.getState();
        var text = update.getMessage().getText();
        Object output;

//        if (CANCEL.equals(text)) {
//            output = cancelProcess(appUser);
//        } else if (BASIC_STATE.equals(appUser.getState())) {
//            output = processServiceCommand();
//        } else if (WAITING_FOR_EMAIL)



        var sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("FROM NODE");

        answerProducer.produceAnswer(sendMessage);
    }

    private String processServiceCommand() {
        return null;
    }

    private String cancelProcess(AppUser appUser) {
        return null;
    }

    private AppUser findOrSaveAppUser(Update update) {
        var message = update.getMessage();
        var telegramUser = message.getFrom();
        AppUser persistentAppUser = appUserRepo.findAppUserByTelegramUserId(telegramUser.getId());
        if (persistentAppUser == null) {
            AppUser transientAppUser = AppUser.builder()
                    .telegramUserId(telegramUser.getId())
                    .username(telegramUser.getUserName())
                    // TODO изменить значение по умолчанию после добаавления регистрации
                    .isActive(true)
                    .state(null)
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
