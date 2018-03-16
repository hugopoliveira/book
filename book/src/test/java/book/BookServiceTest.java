package book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

//@RunWith(SpringRunner.class)
//@WebMvcTest
//@ContextConfiguration(classes = { BookService.class })
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

	@LocalServerPort
	int port;

	@MockBean
	private SaraivaClient saraivaClient;

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private BookService service;

//	@Test
	public void consultaTest() {
		given(this.bookRepository.findById("9731880"))
				.willReturn(Optional.of(new Book("9731880", "Origem", "Arqueiro", 34.90)));
		Book book = service.consulta("9731880");

		RestTemplate restTemplate = new RestTemplate();

		Book book2 = restTemplate.getForObject("http://localhost:"+port+"/book/9731880", Book.class);
		assertThat(book2).isEqualTo(book);
	}
	
	//TODO - Demais tests
}
