package book;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SaraivaClient.class })
public class SaraivaClientTest {

	@Autowired
	private SaraivaClient saraivaClient;

	private SaraivaJson saraivaJson;

	@Before
	public void init() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		saraivaJson = mapper.readValue(
				"{\"sku\": 9731880,\"name\": \"Origem\",\"brand\": \"Arqueiro\",\"price\":{\"bestPrice\":{\"value\":\"32,90\"}}}", SaraivaJson.class);
	}

	@Test
	public void consultaTest() {
		assertThat(saraivaClient.consultarSaraiva("9731880")).isEqualTo(saraivaJson);
	}
}
