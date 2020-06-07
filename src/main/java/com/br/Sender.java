package com.br;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.newConnection()) {
			Channel channel = connection.createChannel();
			channel.queueDeclare("hello-world", false, false, false, null);
			String message = "is this the matrix";
			channel.basicPublish("", "hello-world", false, null, message.getBytes());
			System.out.println("!!! message has been sent!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
