package org.klozevitz.services.interfaces.base;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Main {
    void processCallbackQueryMessage(Update update);
    void processTextMessage(Update update);
    void processCommandMessage(Update update);
    void processDocMessage(Update update);
}