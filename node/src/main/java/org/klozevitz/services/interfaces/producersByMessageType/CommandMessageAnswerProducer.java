package org.klozevitz.services.interfaces.producersByMessageType;

import org.klozevitz.enitites.appUsers.AppUser;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandMessageAnswerProducer {
    void produce(Update update, AppUser currentUser);
}
