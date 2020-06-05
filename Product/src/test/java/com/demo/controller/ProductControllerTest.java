package com.demo.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

import com.product.controller.ProductController;
import com.product.dto.ProductDto;
import com.product.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductControllerTest {
	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	List<ProductDto> listProductDto;
	ProductDto productDto;
	ProductDto productDto1;

	@Before
	public void setup() {
		productDto = new ProductDto();
		productDto1 = new ProductDto();
		listProductDto = new ArrayList<ProductDto>();
	}

	@Test
	public void getProductById() {
		productDto.setProductId("101");
		productDto.setProductName("iphone");
		productDto.setProductQty(2);
		productDto.setProductPrice(4000.00);
		when(productService.getProductDetails("101")).thenReturn(productDto);
		productDto = productController.getProductDetails("101");

	}

	@Test
	public void getAllProducts() {
		productDto.setProductId("101");
		productDto.setProductName("iphone");
		productDto.setProductQty(2);
		productDto.setProductPrice(4000.00);
		productDto1.setProductId("102");
		productDto1.setProductName("samsung");
		productDto1.setProductQty(5);
		productDto1.setProductPrice(2000.00);
		listProductDto.add(productDto);
		listProductDto.add(productDto1);
		when(productService.getAllProducts()).thenReturn(listProductDto);
		listProductDto = productController.getAllProducts();

	}

	@Test
	public void saveProduct() {
		productDto.setProductId("101");
		productDto.setProductName("iphone");
		productDto.setProductQty(2);
		productDto.setProductPrice(4000.00);
		when(productService.saveProduct(productDto)).thenReturn(productDto);
		productDto = productController.saveProduct(productDto);
	}

	@Test
	public void deleteOrder() {
		when(productService.deleteProduct("101")).thenReturn("Requested Product Id Deleted Succssfully :" + 101);
		String result = productController.deleteProduct("101");

	}
}
