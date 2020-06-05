package com.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.dto.ProductDto;
import com.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ProductService productService;

	@GetMapping(path = "/getExample")
	public String getString() {
		log.info("inside getExample ..");
		return "product-service";
	}

	@GetMapping(path = "/getAllProducts")
	public List<ProductDto> getAllProducts() {
		List<ProductDto> response=productService.getAllProducts();
		return response;
	}

	@GetMapping(path = "/getProductById/{productId}")
	public ProductDto getProductDetails(@PathVariable String productId) {
		log.info("get product Details--");
		return productService.getProductDetails(productId);
	}

	@PostMapping(path = "/saveProduct")
	public ProductDto saveProduct(@RequestBody ProductDto productRequest) {
		return productService.saveProduct(productRequest);
	}

	@PutMapping(path = "/updateProduct")
	public ProductDto updateProduct(String productId, @RequestBody ProductDto dto) {
		return productService.updateProduct(productId, dto);
	}

	@DeleteMapping(path = "/deleteProduct/{id}")
	public String deleteProduct(@PathVariable String id) {
		return productService.deleteProduct(id);
	}
}
