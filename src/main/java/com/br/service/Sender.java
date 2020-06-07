package com.br.service;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.br.model.Agenda;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class Sender {

	public void send(Agenda agenda) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.newConnection()) {
			Gson gson = new Gson();
			String json = gson.toJson(agenda);
			Channel channel = connection.createChannel();
			channel.queueDeclare("assemblies", false, false, false, null);
			channel.basicPublish("", "assemblies", false, null, json.getBytes());
			System.out.println("Message sent to the RabbitMQ Successfully");
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
