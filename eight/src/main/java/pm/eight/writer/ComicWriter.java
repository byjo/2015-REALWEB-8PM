package pm.eight.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;

@Component
public class ComicWriter implements ItemWriter<List<Comic>>{

	@Override
	public void write(List<? extends List<Comic>> items) throws Exception {
		for(List<Comic> comics : items ){
			for(Comic comic : comics){
//				System.out.println(comic);
			}
		}
	}
}
