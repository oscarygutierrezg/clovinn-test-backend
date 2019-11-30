package ar.com.clovinn.test.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import ar.com.clovinn.test.model.Product;


/**
* La clase ProductDto cuyos son atributos son enviados o recuperados desde los servicios.
*
* @author  Oscar Gutiérrez
* @version 1.0
* @since   30/11/2019
*/
public class ProductDto {
	
	private long id;
	@NotNull(message="name_required")
	@NotEmpty(message="name_not_empty")
	private String name;
	@NotNull(message="type_required")
	@NotEmpty(message="type_not_empty")
	private String type;
	@NotNull(message="description_required")
	@NotEmpty(message="description_not_empty")
	private String description;
	@NotNull(message="quantity_required")
	private Integer quantity;


    public ProductDto () {
    	
    }
    
    public ProductDto (Product product) {
    	name=product.getName();
    	type=product.getType();
    	description=product.getDescription();
    	quantity=product.getQuantity();
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
	
	
	
	
}
