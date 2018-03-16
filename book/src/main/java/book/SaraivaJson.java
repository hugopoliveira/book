package book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaraivaJson {
	private String sku;
	private String name;
	private String brand;
	private Price price;
	
	public Book getBook() {
		Double price = Double.valueOf(this.price.getBestPrice().getValue().replace(".", "").replace(",", "."));
		return new Book(sku, name, brand, price);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Price {
	private BestPrice bestPrice;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BestPrice {
	private String value;
}
