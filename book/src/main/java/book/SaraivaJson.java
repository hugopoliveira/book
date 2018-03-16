package book;

import lombok.Data;

@Data
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
class Price {
	private BestPrice bestPrice;
}

@Data
class BestPrice {
	private String value;
}
