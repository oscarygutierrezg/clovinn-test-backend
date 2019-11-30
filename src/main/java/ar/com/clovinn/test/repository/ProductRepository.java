package ar.com.clovinn.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.clovinn.test.model.Product;

/**
* La clase ProductRepository es la entidad que se encarga de la persistencia de la entidad Product en base de datos.
*
* @author  Oscar Gutiérrez
* @version 1.0
* @since   30/11/2019
*/
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
