package ar.com.clovinn.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ar.com.clovinn.test.dto.ProductDto;
import ar.com.clovinn.test.exception.ClovinnTestException;
import ar.com.clovinn.test.exception.DataNotFoundException;
import ar.com.clovinn.test.model.Product;
import ar.com.clovinn.test.services.ProductService;


/**
 * La clase ProductController se encarga de exponer la capa de servicios osociados a la entidad Product.
 *
 * @author  Oscar Gutiérrez
 * @version 1.0
 * @since   30/11/2019
 */

@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


	@Autowired
	private ProductService productService;

	@PostMapping
	public Product create(@Valid @RequestBody ProductDto product) {
		LOGGER.info(product.toString());
		try{
			Product p = new Product(product); 
			LOGGER.info(p.toString());
			return productService.create(p);
		} catch (ClovinnTestException e) {
			LOGGER.error(e.getMessage(),e);
			throw new ResponseStatusException(
					HttpStatus.PRECONDITION_REQUIRED, "Product Can Not Create", e);
		}
	}

	@PutMapping
	public Product update(@Valid @RequestBody ProductDto product) {
		LOGGER.info(product.toString());
		try{
			Product p = new Product(product); 
			LOGGER.info(p.toString());
			return productService.update(p);
		} catch (ClovinnTestException e) {
			LOGGER.error(e.getMessage(),e);
			throw new ResponseStatusException(
					HttpStatus.PRECONDITION_REQUIRED, "Product Can Not Update", e);
		}
	}

	@GetMapping("/all")
	public List<Product> findAll() {
		try{
			return  productService.findAll();
		} catch (DataNotFoundException e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Product Not Found", e);
		}
	}

	@GetMapping("/{id}")
	Product one(@PathVariable Long id) {
		try{
			return   productService.one(id);
		} catch (DataNotFoundException e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Product Not Found", e);
		}
	}
}
