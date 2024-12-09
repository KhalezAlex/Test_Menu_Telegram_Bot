package org.klozevitz.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.UpdateProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Service
@AllArgsConstructor
public class UpdateProducerService implements UpdateProducer {
    private final RabbitTemplate rabbitTemplate;
    @Override
    public void produce(String rabbitQueue, Update update) {
        log.debug(update.getMessage().getText());
        rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
}
