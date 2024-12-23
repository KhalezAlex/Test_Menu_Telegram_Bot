package org.klozevitz.services.implementations.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.base.Main;
import org.klozevitz.services.interfaces.base.UpdateConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.RabbitQueue.DOC_UPDATE;
import static org.klozevitz.RabbitQueue.TEXT_UPDATE;

@Log4j
@Service
@RequiredArgsConstructor
public class UpdateConsumerService implements UpdateConsumer {
    private final Main main;

    @Override
    @RabbitListener(queues = TEXT_UPDATE)
    public void consumeTextMessageUpdates(Update update) {
        log.debug("NODE: Text message received");
        main.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = DOC_UPDATE)
    public void consumeDocMessageUpdates(Update update) {
        log.debug("NODE: Doc message received");
    }
}
