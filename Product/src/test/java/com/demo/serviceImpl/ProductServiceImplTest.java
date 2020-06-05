package com.demo.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.product.ProductDetailsApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductDetailsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class ProductServiceImplTest {
	@LocalServerPort
	private int port;
	@Autowired
	MockMvc mockMvc;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testgetAllProducts() throws Exception {
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/getAllProducts")).andReturn();
		System.out.println(mvcResult.getResponse());
	}
	@Test
	public void testSaveProduct() throws Exception {
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/saveProduct",String.class)).andReturn();
		System.out.println(mvcResult.getResponse());
	}
	@Test
	public void testDeleteProduct() throws Exception {
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/101")).andReturn();
		System.out.println(mvcResult.getResponse());
	}
}
