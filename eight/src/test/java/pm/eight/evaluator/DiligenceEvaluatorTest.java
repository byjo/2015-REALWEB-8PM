package pm.eight.evaluator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.domain.Comic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/crawler-servlet.xml")
public class DiligenceEvaluatorTest {

	@Autowired
	DiligenceEvaluator diligenceEvaluator;
	
	public void setDiligenceEvaluator(DiligenceEvaluator diligenceEvaluator) {
		this.diligenceEvaluator = diligenceEvaluator;
	}

	@Test
	public void test() {	
		
		Comic comic = diligenceEvaluator.evaluateDiligence();
		System.out.println(comic.getLink());
	}

}
