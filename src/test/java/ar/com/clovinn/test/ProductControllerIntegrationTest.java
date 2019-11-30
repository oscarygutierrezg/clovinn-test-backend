package ar.com.clovinn.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import ar.com.clovinn.test.controller.ProductController;
import ar.com.clovinn.test.dto.ProductDto;
import ar.com.clovinn.test.exception.ClovinnTestException;
import ar.com.clovinn.test.exception.DataNotFoundException;
import ar.com.clovinn.test.model.Product;
import ar.com.clovinn.test.services.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService service;

	
	@Test
	public void createErrorValidationTest() throws Exception {

		Product productTest = new Product(null, "TestType", "TestDescription", 100);

		ProductDto dto = new ProductDto(productTest);

		productTest.setId(100);
		given(service.create(any(Product.class))).willReturn(productTest);

		mvc.perform(post("/product")
				.content(TestUtil.convertObjectToJsonBytes(dto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void createErrorValidation1Test() throws Exception {

		Product productTest = new Product("Test",null, "TestDescription", 100);

		ProductDto dto = new ProductDto(productTest);

		productTest.setId(100);
		given(service.create(any(Product.class))).willReturn(productTest);

		mvc.perform(post("/product")
				.content(TestUtil.convertObjectToJsonBytes(dto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void createErrorValidation2Test() throws Exception {

		Product productTest = new Product("Test", "TestType", null, 100);

		ProductDto dto = new ProductDto(productTest);

		productTest.setId(100);
		given(service.create(any(Product.class))).willReturn(productTest);

		mvc.perform(post("/product")
				.content(TestUtil.convertObjectToJsonBytes(dto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void createErrorValidation3Test() throws Exception {

		Product productTest = new Product("Test", "TestType", "TestDescription", null);

		ProductDto dto = new ProductDto(productTest);

		productTest.setId(100);
		given(service.create(any(Product.class))).willReturn(productTest);

		mvc.perform(post("/product")
				.content(TestUtil.convertObjectToJsonBytes(dto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void createOkTest() throws Exception {

		Product productTest = new Product("Test", "TestType", "TestDescription", 100);

		ProductDto dto = new ProductDto(productTest);

		productTest.setId(100);
		given(service.create(any(Product.class))).willReturn(productTest);

		mvc.perform(post("/product")
				.content(TestUtil.convertObjectToJsonBytes(dto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(100)));
	}
	
	

	@Test
	public void createErrorTest() throws Exception {

		when(service.create(any(Product.class))).thenThrow(new ClovinnTestException("Test Error"));
		
		Product productTest = new Product("Test", "TestType", "TestDescription", 100);

		ProductDto dto = new ProductDto(productTest);

		// when
		MockHttpServletResponse response = mvc.perform(
				post("/product")
				.content(TestUtil.convertObjectToJsonBytes(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertEquals( HttpStatus.PRECONDITION_REQUIRED.value(), response.getStatus());
	}
	
	@Test
		public void updateOkTest() throws Exception {

			Product productTest = new Product("Test", "TestType", "TestDescription", 100);

			ProductDto dto = new ProductDto(productTest);

			productTest.setId(100);
			given(service.update((any(Product.class)))).willReturn(productTest);

			mvc.perform(put("/product")
					.content(TestUtil.convertObjectToJsonBytes(dto))
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(productTest.getName())));
		}
		
		

		@Test
		public void updateErrorTest() throws Exception {

			when(service.update(any(Product.class))).thenThrow(new ClovinnTestException("Test Error"));
			
			Product productTest = new Product("Test", "TestType", "TestDescription", 100);

			ProductDto dto = new ProductDto(productTest);

			// when
			MockHttpServletResponse response = mvc.perform(
					put("/product")
					.content(TestUtil.convertObjectToJsonBytes(dto))
					.contentType(MediaType.APPLICATION_JSON))
					.andReturn().getResponse();
			assertEquals( HttpStatus.PRECONDITION_REQUIRED.value(), response.getStatus());
		}



	@Test
	public void findAllOkTest()
			throws Exception {

		Product productTest = new Product("Test", "TestType", "TestDescription", 100);

		List<Product> products = Arrays.asList(productTest);

		given(service.findAll()).willReturn(products);

		mvc.perform(get("/product/all")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].name", is(productTest.getName())));
	}

	@Test
	public void findAllErrorTest() throws Exception {

		when(service.findAll()).thenThrow(new DataNotFoundException("Test Error"));


		// when
		MockHttpServletResponse response = mvc.perform(
				get("/product/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertEquals( HttpStatus.NOT_FOUND.value(), response.getStatus());
	}




}

