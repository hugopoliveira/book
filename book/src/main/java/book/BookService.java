package book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
public class BookService {
	
	@Autowired
	SaraivaClient client;

	@PostMapping("/book/")
	public void inclusao() {
		//TODO - recuperar valor SKU do request
		System.out.println(client.cunsultarSaraiva("9731880"));
		//TODO - Persistir na base de dados
		//TODO - retorno HTTP 201
	}

	@DeleteMapping("/book/{sku}")
	public ResponseEntity<HttpStatus> exclusao(@PathVariable String sku) {
		//TODO - deletar da base de dados o sku
		//TODO - retorno HTTP 204
		System.out.println(sku);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/book/{sku}")
	public Book consulta(@PathVariable String sku) {
		//TODO - buscar da base de dados o sku
		//TODO - retorno HTTP 200
		System.out.println(sku);
		return new Book("sku", "name", "brand", 0.0);
	}

	@GetMapping("/book/")
	public List<Book> listagem(@RequestParam("price") String price, @RequestParam("limit") String limit) {
		//TODO - buscar da base de dados a lista
		//TODO - retorno HTTP 200
		System.out.println(price);
		System.out.println(limit);
		
		Book book = new Book("sku", "name", "brand", 0.0);
		
		List<Book> results = Lists.newArrayList(book);
		
		return results;
	}
}
