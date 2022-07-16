package com.gunish.nottyfi.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gunish.nottyfi.exception.ExceptionMapper;
import com.gunish.nottyfi.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    private static final ObjectMapper mapper=new ObjectMapper();
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "notification",groupId ="notification-group-id",containerFactory ="kakfaListenerContainerFactory")
    public void listenNotification(String data)
    {
        Notification notification=fromJson(data,Notification.class);
        log.info("Consumed Message "+data);
        template.convertAndSend("/topic/notify",notification);
    }

    private <T> T fromJson(String json,Class<T> tClass)
    {
        try {
            return mapper.readValue(json,tClass);
        }
        catch (Exception e)
        {
            throw new ExceptionMapper(e.getMessage());
        }
    }

}
