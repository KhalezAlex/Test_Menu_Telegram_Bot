package org.klozevitz.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.Main;
import org.klozevitz.services.interfaces.UpdateConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.RabbitQueue.DOC_MESSAGE_UPDATE;
import static org.klozevitz.RabbitQueue.TEXT_MESSAGE_UPDATE;

@Log4j
@Service
@RequiredArgsConstructor
public class UpdateConsumerService implements UpdateConsumer {
    private final Main main;

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessageUpdates(Update update) {
        log.debug("NODE: Text message received");
        main.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeDocMessageUpdates(Update update) {
        log.debug("NODE: Doc message received");
    }
}
