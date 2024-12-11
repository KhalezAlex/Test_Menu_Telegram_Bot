package org.klozevitz.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.jvnet.hk2.annotations.Service;
import org.klozevitz.services.interfaces.AnswerProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
