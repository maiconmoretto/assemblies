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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.br.model.Voting;
import com.br.repository.VotingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingRepositoryTest {

	@Mock
	private VotingRepository votingRepository;

	private Voting voting;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		voting = new Voting(1, 1, "Sim", "01-01-01 01:01:01");
	}

	@Test
	public void findAll() {
		List<Voting> votingList = new ArrayList<Voting>();
		votingList.add(new Voting(1, 1, "Sim", "01-01-01 01:01:01"));
		votingList.add(new Voting(2, 2, "Não", "02-02-02 02:02:02"));
		votingList.add(new Voting(3, 3, "Sim", "03-03-03 03:03:03"));
		when(votingRepository.findAll()).thenReturn(votingList);

		List<Voting> result = (List<Voting>) votingRepository.findAll();
		assertEquals(3, result.size());
	}

	@Test
	public void deleteById() {
		votingRepository.deleteById(voting.getId());
		verify(votingRepository, times(1)).deleteById(voting.getId());
	}

	@Test
	public void save() {
		Voting voting = new Voting(1, 1, "Sim", "01-01-01 01:01:01");
		when(votingRepository.save(voting)).thenReturn(voting);
		Voting result = votingRepository.save(voting);
		assertEquals("01-01-01 01:01:01", result.getCreatedAt());
		assertEquals(1, result.getIdUser());
		assertEquals(1, result.getIdAgenda());
		assertEquals("Sim", result.getVote());
	}
	
	@Test
	public void update() {
		when(votingRepository.save(voting)).thenReturn(voting);
		Voting result = votingRepository.save(voting);
		assertEquals("01-01-01 01:01:01", result.getCreatedAt());
		assertEquals(1, result.getIdUser());
		assertEquals(1, result.getIdAgenda());
		assertEquals("Sim", result.getVote());
	}

	@Test
	public void findById() {
		Optional<Voting> voting = Optional.of(new Voting(1, 1, "Sim", "01-01-01 01:01:01"));
		when(votingRepository.findById(1)).thenReturn(voting);
		Optional<Voting> result = votingRepository.findById(1);
		assertEquals("01-01-01 01:01:01", result.get().getCreatedAt());
		assertEquals(1, result.get().getIdUser());
		assertEquals(1, result.get().getIdAgenda());
		assertEquals("Sim", result.get().getVote());
	}
}