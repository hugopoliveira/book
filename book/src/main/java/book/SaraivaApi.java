package book;

import lombok.Data;

@Data
public class SaraivaApi {
	private String sku;
	private String name;
	private String brand;
	private Price price;
	
	public Book getBook() {
		return new Book(sku, name, brand, Double.valueOf(price.getBestPrice().getValue()));
	}
}

@Data
class Price {
	private BestPrice bestPrice;
}

@Data
class BestPrice {
	private String value;
}
