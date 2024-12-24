package org.klozevitz.services.interfaces.producersByMessageType;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface DocumentMessageAnswerProducer {
    void produce(Update update);
}
