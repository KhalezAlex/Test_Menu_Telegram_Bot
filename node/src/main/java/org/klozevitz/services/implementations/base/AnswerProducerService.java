package org.klozevitz.services.implementations.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.base.AnswerProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.klozevitz.RabbitQueue.ANSWER_MESSAGE;

@Log4j
@Service
@RequiredArgsConstructor
public class AnswerProducerService implements AnswerProducer {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void produceAnswer(SendMessage sendMessage) {
        rabbitTemplate.convertAndSend(ANSWER_MESSAGE, sendMessage);
    }
}
