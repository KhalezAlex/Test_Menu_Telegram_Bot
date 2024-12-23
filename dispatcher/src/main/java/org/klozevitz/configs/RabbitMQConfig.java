package org.klozevitz.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.klozevitz.RabbitQueue.*;

@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue textMessageQueue() {
        return new Queue(TEXT_UPDATE);
    }

    @Bean
    public Queue commandQueue() {return new Queue(COMMAND_UPDATE);}

    @Bean
    public Queue callbackQueue() {return new Queue(CALLBACK_QUERY_UPDATE);}


    @Bean
    public Queue docMessageQueue() {
        return new Queue(DOC_UPDATE);
    }

    @Bean
    public Queue answerMessageQueue() {
        return new Queue(ANSWER_MESSAGE);
    }
}