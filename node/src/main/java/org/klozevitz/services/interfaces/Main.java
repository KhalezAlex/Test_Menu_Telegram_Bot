package org.klozevitz.services.interfaces;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Main {
    void processTextMessage(Update update);
}