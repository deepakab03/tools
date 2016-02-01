package com.deepak.tools.rabbitmq;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.deepak.tools.model.Data;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String QUEUE_NAME = "hello1";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
			
		try {
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			String message = "Hello World!";
//		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			Data data = new Data("Deepak", 35, Collections.singletonList("yakyak"));
			channel.basicPublish("", QUEUE_NAME, null, SerializationUtils.serialize(data));
		    System.out.println(" [x] Sent '" + message + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			channel.close();
			connection.close();
			
		}
	}
}
