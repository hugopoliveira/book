package book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookService {

	@Autowired
	private SaraivaClient client;

	@Autowired
	private BookRepository repository;

	@PostMapping("/book/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void inclusao(@RequestParam("sku") String sku) {
		Book book = client.consultarSaraiva(sku).getBook();

		if (book.getSku() != null)
			repository.save(book);
	}

	@DeleteMapping("/book/{sku}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void exclusao(@PathVariable String sku) {
		repository.deleteById(sku);
	}

	@GetMapping("/book/{sku}")
	public Book consulta(@PathVariable String sku) {
		return repository.findById(sku).get();
	}

	@GetMapping("/book/")
	public Iterable<Book> listagem(@RequestParam(value = "price", required = false) Double price,
			@RequestParam(value = "limit", required = false) Integer limit) {
		Iterable<Book> results = null;

		// A busca abaixo poderia ser melhor com a implementação de queryDSL
		if (price != null && limit != null) {
			results = repository.findByPriceLessThanEqual(price, PageRequest.of(0, limit)).getContent();
		} else if (price != null) {
			results = repository.findByPriceLessThanEqual(price);
		} else if (limit != null) {
			results = repository.findAll(PageRequest.of(0, limit)).getContent();
		} else {
			results = repository.findAll();
		}

		return results;
	}
}
