package pm.eight.evaluator;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.domain.Episode;
import pm.eight.repository.EpisodeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class DiligenceEvaluatorTest {
	private static final String EPISODE_PAGE_URL = "http://comic.naver.com/webtoon/detail.nhn?titleId=570503&no=92&weekday=thu";
	private static final String COMIC_PAGE_URL = "http://comic.naver.com/webtoon/list.nhn?titleId=183559&weekday=mon";

	@Autowired
	DiligenceEvaluator diligenceEvaluator;
	
	@Autowired
	EpisodeRepository episodeRepository;
	
	@Test
	public void evalateTest() throws IOException {	
		Episode episode = (Episode) episodeRepository.findLatestEpisode(1);
		System.out.println(episode);
		episode.setId((long)2);
		diligenceEvaluator.evaluate(episode);
		
	}

}
