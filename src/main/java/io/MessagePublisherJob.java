package io;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherJob  {

    @Autowired
    private RabbitTemplate amqpTemplate;
    private static final String EXCHANGE_NAME = RabbitConfig.EXCHANGE_NAME;
    private static final String ROUTING_KEY = RabbitConfig.ROUTING_KEY;

    @Scheduled(fixedRate = 100)
    protected void executeInternal(){
        String message = "Message " + System.currentTimeMillis();
        amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        System.out.println("Published message: " + message);
    }

}
