package io;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessagePublisherJob implements Job {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String EXCHANGE_NAME = RabbitConfig.EXCHANGE_NAME;
    private static final String ROUTING_KEY = RabbitConfig.ROUTING_KEY;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String message = "Message " + System.currentTimeMillis();
        amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        System.out.println("Published message: " + message);
    }

}
