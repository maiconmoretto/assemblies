package com.br.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Agenda;
import com.br.repository.AgendaRepository;
import com.br.OrderQueueSender;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	OrderQueueSender orderQueueSender;

	@Autowired
	AgendaRepository agendaRepository;

	@GetMapping(value = "/api/v1/producer")
	public String producer(@RequestParam("id") int id) {
		Optional<Agenda> agenda = agendaRepository.findById(id);
		String result = "The resul of the agenda was: " + "sim : " + agenda.get().getSim() 
				+ ", não : " + agenda.get().getNao();
		orderQueueSender.send(result);

		return "Message sent to the RabbitMQ Successfully";
	}

}