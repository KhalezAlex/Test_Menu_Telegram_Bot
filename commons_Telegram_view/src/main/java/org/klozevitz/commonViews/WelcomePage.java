package org.klozevitz.commonViews;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class WelcomePage {
    public static SendMessage unregisteredUsersWelcomePage(Update update) {
        SendMessage sendMessage = new SendMessage();
        var keyboard = new InlineKeyboardMarkup();
        var row = List.of(
                button("КОМПАНИЯ", "/company"),
                button("РАБОТНИК", "/employee")
        );
        var keyboardRows = List.of(row);
        keyboard.setKeyboard(keyboardRows);
        sendMessage.setText("Добро пожаловать в чат бот");
        sendMessage.setReplyMarkup(keyboard);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }

    public static SendMessage companyWelcomePage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Company welcome page");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }

    public static SendMessage departmentWelcomePage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Department welcome page");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }

    public static SendMessage employeeWelcomePage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Employee welcome page");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }

    public static SendMessage adminWelcomePage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Admin welcome page");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }



    public static InlineKeyboardButton button(String text, String callbackData) {
        var button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
