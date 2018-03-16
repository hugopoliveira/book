package book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, String> {

	Page<Book> findByPriceLessThanEqual(double price, Pageable pageable);
	List<Book> findByPriceLessThanEqual(double price);
}