package ar.com.clovinn.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.clovinn.test.model.Product;
import ar.com.clovinn.test.repository.ProductRepository;
import ar.com.clovinn.test.services.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceIntegrationTest {
	
	
	@TestConfiguration
    static class ClovinnTestBakendApplication {
  
        @Bean
        public ProductService productService() {
            return new ProductService();
        }
    }



    @MockBean
    private ProductRepository productRepository;


    
    @Autowired
    private ProductService employeeService;
	

	
	
	@Test
	public void createOkTest() throws Exception {

		Product productTest = new Product("Test", "TestType", "TestDescription", 100);
		when(productRepository.save(any(Product.class)))
	      .thenReturn(productTest);
		 Product found = employeeService.create(productTest);
		  
	     assertThat(found.getName())
	      .isEqualTo(productTest.getName()); 

	}
	
	
	@Test
	public void updateOkTest() throws Exception {

		Product productTest = new Product("Test", "TestType", "TestDescription", 100);
		when(productRepository.saveAndFlush(any(Product.class)))
	      .thenReturn(productTest);
		 Product found = employeeService.update(productTest);
		  
	     assertThat(found.getName())
	      .isEqualTo(productTest.getName());

	}
	
	
	@Test
	public void getAllOkTest() throws Exception {


		List<Product> products = Arrays.asList(new Product("Test", "TestType", "TestDescription", 100),new Product("Test1", "TestType1", "TestDescription1", 100), new Product("Test2", "TestType2", "TestDescription2", 100));
		
		
		given(productRepository.findAll()).willReturn(products);

		List<Product> found = employeeService.findAll();
		  
	     assertThat(found.size())
	      .isEqualTo(3);

	}
}

