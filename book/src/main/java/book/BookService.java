package book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookService {

	@Autowired
	private SaraivaClient client;

	@Autowired
	private BookRepository repository;

	@PostMapping("/book/")
	public void inclusao() {
		// TODO - recuperar valor SKU do request
		Book book = client.cunsultarSaraiva("9731880").getBook();

		if (book.getSku() != null)
			repository.save(book);
		// TODO - retorno HTTP 201
	}

	@DeleteMapping("/book/{sku}")
	public void exclusao(@PathVariable String sku) {
		repository.deleteById(sku);
		// TODO - retorno HTTP 204
	}

	@GetMapping("/book/{sku}")
	public Book consulta(@PathVariable String sku) {
		// TODO - retorno HTTP 200
		return repository.findById(sku).get();
	}

	@GetMapping("/book/")
	public Iterable<Book> listagem(@RequestParam(name = "price", required = false) Double price,
			@RequestParam(name = "limit", required = false) Integer limit) {
		// TODO - retorno HTTP 200

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
