package com.redhat.service.amqp;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.service.config.AMQPConfig;
import com.redhat.service.config.WebSocketConfig;

@Service
public class AMQPService {

	@Autowired
	protected RabbitMessagingTemplate rabbitMessageTemplate;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public <T extends Serializable> void sendMessage(T object) {
		try {
			String json = new ObjectMapper().writeValueAsString(object);
			rabbitMessageTemplate.convertAndSend(AMQPConfig.EXCHANGE_NAME, "", json);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RabbitListener(queues = AMQPConfig.QUEUE)
	public void consumer(Message message) {
		logger.info("Receive message with body -> {}", new String(message.getBody()));
		simpMessagingTemplate.convertAndSend(WebSocketConfig.BROKER, new String(message.getBody()));
	}

}
