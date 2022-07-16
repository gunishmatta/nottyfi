package com.gunish.nottyfi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gunish.nottyfi.exception.ExceptionMapper;
import com.gunish.nottyfi.model.Notification;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final ObjectMapper mapper=new ObjectMapper();
    private final BrokerProducerService brokerProducerService;
    private final Environment environment;

    public NotificationServiceImpl(BrokerProducerService brokerProducerService, Environment environment) {
        this.brokerProducerService = brokerProducerService;
        this.environment = environment;
    }


    @Override
    public void send(Notification notification) {
        brokerProducerService.sendMessage(environment.getProperty("producer.kafka.topic-name"),toJson(notification));
    }

    private <T> String toJson(T object)
    {
        try {
            return mapper.writeValueAsString(object);
        }
        catch (Exception exception)
        {
            throw new ExceptionMapper(exception.getMessage());
        }
    }

}
