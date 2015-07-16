package pm.eight.evaluator;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.enums.WebtoonStateType;
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
		Long id = (long) 1;
		Episode episode = (Episode) episodeRepository.findLatestEpisode();
	
		System.out.println(episode);
		episode.setId((long)2);
		//		assertEquals(episode.getId(), 2);
//		diligenceEvaluator.evaluate(episode);
//		assertEquals("!", "!");
// 		테스트 실패
	}

}
