package org.klozevitz.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.jvnet.hk2.annotations.Service;
import org.klozevitz.services.interfaces.AnswerProducer;
import org.klozevitz.services.interfaces.UpdateConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.RabbitQueue.DOC_MESSAGE_UPDATE;
import static org.klozevitz.RabbitQueue.TEXT_MESSAGE_UPDATE;

@Log4j
@Service
@RequiredArgsConstructor
public class UpdateConsumerService implements UpdateConsumer {
    private final AnswerProducer answerProducer;

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessageUpdates(Update update) {
        log.debug("NODE: Text message received");

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("FROM NODE");

        answerProducer.produceAnswer(sendMessage);
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeDocMessageUpdates(Update update) {
        log.debug("NODE: Doc message received");
    }
}
