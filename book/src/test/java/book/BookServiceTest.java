package book;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookService.class)
@ContextConfiguration(classes = BookService.class)
public class BookServiceTest {

	@MockBean
	private SaraivaClient saraivaClient;

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void inclusaoTest() throws Exception {
		given(this.saraivaClient.consultarSaraiva("9731880"))
				.willReturn(new SaraivaJson("9731880", "Origem", "Arqueiro", new Price(new BestPrice("34.90"))));

		mvc.perform(post("/book/").param("sku", "9731880")).andExpect(status().isCreated());
	}

	@Test
	public void exclusaoTest() throws Exception {
		mvc.perform(delete("/book/9731880/")).andExpect(status().isNoContent());
	}

	@Test
	public void consultaTest() throws Exception {
		given(this.bookRepository.findById("9731880"))
				.willReturn(Optional.of(new Book("9731880", "Origem", "Arqueiro", 34.90)));

		mvc.perform(get("/book/9731880/")).andExpect(status().isOk()).andExpect(
				content().json("{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90}"));
	}

	@Test
	public void listagemSemParametrosTest() throws Exception {
		given(this.bookRepository.findAll())
				.willReturn(Lists.newArrayList(new Book("9731880", "Origem", "Arqueiro", 34.90)));

		mvc.perform(get("/book/")).andExpect(status().isOk()).andExpect(
				content().json("[{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90}]"));
	}

	@Test
	public void listagemTest() throws Exception {
		given(this.bookRepository.findByPriceLessThanEqual(35, PageRequest.of(0, 2)))
				.willReturn(new PageImpl<>(Lists.newArrayList(new Book("9731880", "Origem", "Arqueiro", 34.90),
						new Book("9731880", "Origem", "Arqueiro", 34.90))));

		mvc.perform(get("/book/?price=35&limit=2")).andExpect(status().isOk()).andExpect(content().json(
				"[{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90},{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90}]"));
	}

	@Test
	public void listagemSemParametroLimitTest() throws Exception {
		given(this.bookRepository.findByPriceLessThanEqual(35)).willReturn(Lists.newArrayList(
				new Book("9731880", "Origem", "Arqueiro", 34.90), new Book("9731880", "Origem", "Arqueiro", 34.90)));

		mvc.perform(get("/book/?price=35")).andExpect(status().isOk()).andExpect(content().json(
				"[{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90},{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90}]"));
	}

	@Test
	public void listagemSemParametroPriceTest() throws Exception {
		given(this.bookRepository.findAll(PageRequest.of(0, 2)))
				.willReturn(new PageImpl<>(Lists.newArrayList(new Book("9731880", "Origem", "Arqueiro", 34.90),
						new Book("9731880", "Origem", "Arqueiro", 34.90))));

		mvc.perform(get("/book/?limit=2")).andExpect(status().isOk()).andExpect(content().json(
				"[{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90},{\"sku\":\"9731880\",\"name\":\"Origem\",\"brand\":\"Arqueiro\",\"price\":34.90}]"));
	}
}