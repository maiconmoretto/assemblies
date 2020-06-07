package com.br.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.model.Agenda;
import com.br.service.AgendaService;
import com.br.service.Sender;
import com.br.service.Consumer;


@RestController
@RequestMapping(value = "/api/v1/rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	AgendaService agendaService;

	@Autowired
	Sender sender;
	
	@Autowired
	Consumer consumer;

	@GetMapping(value = "/producer")
	public void producer(@RequestParam("id") int id) throws IOException, TimeoutException {
		Agenda agenda = agendaService.findAgendaClosedById(id);
		
		sender.send(agenda);
	}
	
	@GetMapping(value = "/producer/all/")
	public void producerAll() throws IOException, TimeoutException {
		List<Agenda> agendasClosed = agendaService.agendasClosed();	
		
		for(Agenda agenda: agendasClosed) {
			sender.send(agenda);
		}			
	}
	
	@GetMapping(value = "/consumer")
	public void producer() throws IOException, TimeoutException {		
		consumer.consume();
	}
	
}
