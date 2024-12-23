package org.klozevitz.services.interfaces.base;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateConsumer {
    void consumeCallbackQueryUpdates(Update update);
    void consumeTextUpdates(Update update);
    void consumeCommandUpdates(Update update);
    void consumeDocUpdates(Update update);
}
