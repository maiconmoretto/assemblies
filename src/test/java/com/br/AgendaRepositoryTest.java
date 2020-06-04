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
import com.br.model.Agenda;
import com.br.repository.AgendaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AgendaRepositoryTest {

	@Mock
	private AgendaRepository agendaRepository;

	private Agenda agenda;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		agenda = new Agenda("agenda 1", "01-01-01", 60, 0, 0);
	}

	@Test
	public void findAll() {
		List<Agenda> agendaList = new ArrayList<Agenda>();
		agendaList.add(new Agenda("agenda 1", "01-01-01", 60, 0, 0));
		agendaList.add(new Agenda("agenda 2", "02-02-02", 120, 0, 0));
		agendaList.add(new Agenda("agenda 3", "03-03-03", 180, 0, 0));
		when(agendaRepository.findAll()).thenReturn(agendaList);
		List<Agenda> result = agendaRepository.findAll();
		assertEquals(3, result.size());
	}

	@Test
	public void deleteById() {
		agendaRepository.deleteById(agenda.getId());
		verify(agendaRepository, times(1)).deleteById(agenda.getId());
	}

	@Test
	public void save() {
		when(agendaRepository.save(agenda)).thenReturn(agenda);
		Agenda result = agendaRepository.save(agenda);
		assertEquals("agenda 1", result.getDescription());
		assertEquals("01-01-01", result.getCreatedAt());
		assertEquals(60, result.getDuration());
		assertEquals(0, result.getNao());
		assertEquals(0, result.getSim());
	}

	@Test
	public void update() {
		when(agendaRepository.save(agenda)).thenReturn(agenda);
		Agenda result = agendaRepository.save(agenda);
		assertEquals("agenda 1", result.getDescription());
		assertEquals("01-01-01", result.getCreatedAt());
		assertEquals(60, result.getDuration());
		assertEquals(0, result.getNao());
		assertEquals(0, result.getSim());
	}

	@Test
	public void findById() {
		Optional<Agenda> agenda = Optional.of(new Agenda("agenda 1", "01-01-01", 60, 0, 0));
		when(agendaRepository.findById(1)).thenReturn(agenda);
		Optional<Agenda> result = agendaRepository.findById(1);
		assertEquals("agenda 1", result.get().getDescription());
		assertEquals("01-01-01", result.get().getCreatedAt());
		assertEquals(60, result.get().getDuration());
		assertEquals(0, result.get().getNao());
		assertEquals(0, result.get().getSim());
	}

}