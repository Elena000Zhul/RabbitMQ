package io;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail messagePublisherJobDetail() {
        return JobBuilder.newJob(MessagePublisherJob.class)
                .withIdentity("messagePublisherJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger messagePublisherJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(messagePublisherJobDetail())
                .withIdentity("messagePublisherTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(100)
                        .repeatForever())
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobDetails(messagePublisherJobDetail());
        factory.setTriggers(messagePublisherJobTrigger());
        return factory;
    }
}
