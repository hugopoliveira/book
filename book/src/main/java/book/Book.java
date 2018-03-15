package book;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Book {

	@Id
	private String sku;
	private String name;
	private String brand;
	private double price;
}