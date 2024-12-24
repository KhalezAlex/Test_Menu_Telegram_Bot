package org.klozevitz.services.implementations.producersByMessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.services.interfaces.base.AnswerProducer;
import org.klozevitz.services.interfaces.producersByMessageType.TextMessageAnswerProducer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Service
@RequiredArgsConstructor
public class TextMessageAnswerProducerService implements TextMessageAnswerProducer {
    private final AnswerProducer answerProducer;

    @Override
    public void produce(Update update) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("FROM NODE - text message response");

        answerProducer.produceAnswer(sendMessage);
    }
}
