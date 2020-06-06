package com.br.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Agenda;
import com.br.repository.AgendaRepository;
import com.br.AgendaQueueSender;

@RestController
@RequestMapping(value = "/api/v1/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	AgendaQueueSender agendaQueueSender;

	@Autowired
	AgendaRepository agendaRepository;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("id") int id) {
		Optional<Agenda> agenda = agendaRepository.findById(id);
		String result = "The resul of the agenda with id: " + id + " and description :" + agenda.get().getDescription()
				+ " was: " + "sim : " + agenda.get().getSim() + ", não : " + agenda.get().getNao();
		agendaQueueSender.send(result);

		return "Message sent to the RabbitMQ Successfully";
	}

}