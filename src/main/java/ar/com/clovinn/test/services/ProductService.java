package ar.com.clovinn.test.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.clovinn.test.exception.DataNotFoundException;
import ar.com.clovinn.test.model.Product;
import ar.com.clovinn.test.repository.ProductRepository;


/**
 * La clase CustomerService es la bean que se encarga de invocar los métodos del repositorio y agregar cierta lógica en caso que se necesite.
 *
 * @author  Oscar Gutiérrez
 * @version 1.0
 * @since   30/11/2019
 */
@Service 
@Transactional
public class ProductService {


	@Autowired
	private ProductRepository productRepository;   

	/**
	 * Este método crear un nuevo producto.
	 * @param p Producto a crear
	 * @return Producto a creado
	 */
	public Product create(final Product p) {
		return productRepository.save(p);
	}

	/**
	 * Este método modifica un producto.
	 * @param p Producto a modificar
	 * @return Producto a actualizado
	 */
	public Product update(final Product p) {
		return productRepository.saveAndFlush(p);
	}

	/** 
	 * Este método borra un producto.
	 * @param p Producto a borrar
	 */
	public void delete(final Product p) {
		productRepository.delete(p);
	}

	/**
	 * Este método obtiene todos los productos guardados.
	 * @return List<Product> Lista de productos
	 * @see List
	 * @see Product
	 */
	public List<Product> findAll() {
		return productRepository.findAll();
	}


	/**
	 * Este método obtiene un producto dado el  id.
	 * @param id del producto a buscar
	 * @return Product Producto consultado
	 * @throws En caso que el id no este registrado
	 * @see Product
	 */
	public Product one(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException(id+""));
	}
}
