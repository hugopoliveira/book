package book;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SaraivaJsonTest {

	private String json = "{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":{\"bestPrice\":{\"value\":\"32,9\"}}}";
	private Book book = new Book("9731880", "Origem", "Arqueiro", 32.9);
	
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {   
		ObjectMapper mapper = new ObjectMapper();
        
		SaraivaJson saraivaJson = mapper.readValue(json, SaraivaJson.class);
			
		assertThat(saraivaJson.getBook()).isEqualTo(book);
	}

}