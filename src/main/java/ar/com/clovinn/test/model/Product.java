package ar.com.clovinn.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.com.clovinn.test.dto.ProductDto;


/**
* La clase Product es la entidad que va a representar los productos en el contexto de la aplicación.
*
* @author  Oscar Gutiérrez
* @version 1.0
* @since   30/11/2019
*/
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String type;
	private String description;
	private Integer quantity;
	
	public Product() {
	}
	
	
	
	public Product(String name, String type, String description, Integer quantity) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
		this.quantity = quantity; 
	}



	public Product(ProductDto productDto) {
		id = productDto.getId();
	  	name=productDto.getName();
    	type=productDto.getType();
    	description=productDto.getDescription();
    	quantity=productDto.getQuantity();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description
				+ ", quantity=" + quantity + "]";
	}
	
	
	
}
