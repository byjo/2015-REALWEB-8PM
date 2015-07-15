package pm.eight.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.repository.ComicRepository;

@Component
public class ComicWriter implements ItemWriter<List<Comic>>{
	@Autowired
	ComicRepository repository;
	
	@Override
	public void write(List<? extends List<Comic>> items) throws Exception {
		for(List<Comic> comics : items ){
			for(Comic comic : comics){
				repository.save(comic);
//				System.out.println(comic);
			}
		}
	}
}
