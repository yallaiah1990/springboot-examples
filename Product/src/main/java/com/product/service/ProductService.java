package com.product.service;
import java.util.List;

import com.product.dto.ProductDto;

public interface ProductService {

	List<ProductDto> getAllProducts();

	ProductDto getProductDetails(String productId);

	ProductDto saveProduct(ProductDto productRequest);

	String deleteProduct(String id);
	
	ProductDto updateProduct(String productId,ProductDto productRequest);

}
