/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.br;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.br.model.Agenda;
import com.br.service.AgendaService;
import com.br.service.Consumer;
import com.br.service.Sender;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	AgendaService agendaService;

	@Autowired
	Sender sender;
	
	@Autowired
	Consumer consumer;
	
	@Scheduled(fixedRate = 10000)
	public void verifyAllAgendasClosedAndCreateProducer() throws IOException, TimeoutException {
	List<Agenda> agendasClosed = agendaService.agendasClosed();	
	log.info("Task start {}", dateFormat.format(new Date()));
		for(Agenda agenda: agendasClosed) {
			sender.send(agenda);
			agenda.setSentToMessager(true);
			agendaService.save(agenda);
		}	
	}	
}