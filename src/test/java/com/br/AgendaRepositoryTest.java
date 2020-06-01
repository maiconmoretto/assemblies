package com.br;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.model.Agenda;
import com.br.repository.AgendaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AgendaRepositoryTest {

	@Mock
	private AgendaRepository agendaRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	public void findAll() {
//		List<Agenda> agendaList = new ArrayList<Agenda>();
//		agendaList.add(new Agenda("agenda 1", "01-01-01", 60, 0, 0));
//		agendaList.add(new Agenda("agenda 2", "02-02-02", 120, 0, 0));
//		agendaList.add(new Agenda("agenda 3", "03-03-03", 180, 0, 0));
//		when(agendaRepository.findAll()).thenReturn(agendaList);
//
//		List<Agenda> result = agendaRepository.findAll();
//		assertEquals(3, result.size());
//	}
//
//	@Test
//	public void deleteById() {
//		Agenda agenda = new Agenda("agenda 1", "01-01-01", 60, 0, 0);
//		agendaRepository.deleteById(agenda.getId());
//		verify(agendaRepository, times(1)).deleteById(agenda.getId());
//	}
//
//	@Test
//	public void add() {
//		Agenda agenda = new Agenda("agenda 1", "01-01-01", 60, 0, 0);
//		when(agendaRepository.save(agenda)).thenReturn(agenda);
//		Agenda result = agendaRepository.save(agenda);
//		assertEquals("agenda 1", result.getDescription());
//		assertEquals("01-01-01", result.getCreatedAt());
//		assertEquals(60, result.getDuration());
//		assertEquals(0, result.getNao());
//		assertEquals(0, result.getSim());
//	}

}