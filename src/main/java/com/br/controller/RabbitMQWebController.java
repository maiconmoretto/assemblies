package com.br.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.model.Agenda;
import com.br.repository.AgendaRepository;
import com.br.service.Sender;


@RestController
@RequestMapping(value = "/api/v1/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	AgendaRepository agendaRepository;

	@Autowired
	Sender sender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("id") int id) throws IOException, TimeoutException {
		Agenda agenda = agendaRepository.findAgendaClosedById(id);
		sender.send(agenda);

		return "Message sent to the RabbitMQ Successfully";
	}
}
