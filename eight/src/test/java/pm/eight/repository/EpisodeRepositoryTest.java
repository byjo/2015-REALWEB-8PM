package pm.eight.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.domain.Episode;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/crawler-servlet.xml")
public class EpisodeRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(EpisodeRepositoryTest.class);
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	@Test
	public void findLatestEpisode() {
//		Episode episode = episodeRepository.findLatestEpisode(1);
//		System.out.println(episode);
//		System.out.println(episode.getComic());
		//TEST 실패;
	}

}