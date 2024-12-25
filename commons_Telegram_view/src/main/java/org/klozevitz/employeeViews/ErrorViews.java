package org.klozevitz.employeeViews;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorViews {
    public static SendMessage unknownCallbackServiceCommandErrorView(Update update) {
        var answer = new SendMessage();
        answer.setText("Введена некорректная команда. Вам доступны следующие команды:\n" +
                "/start\n" +
                "/help\n");
        answer.setChatId(update.getCallbackQuery().getMessage().getChatId());
        return answer;
    }

    public static SendMessage userSelfRegistrationErrorView(Update update) {
        var answer = new SendMessage();
        answer.setText("Компания еще не добавила Вас в список сотрудников. Регистрация невозможна.");
        answer.setChatId(update.getCallbackQuery().getMessage().getChatId());
        return answer;
    }
}
