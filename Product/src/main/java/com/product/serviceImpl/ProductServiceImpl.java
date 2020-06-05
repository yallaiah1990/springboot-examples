package com.product.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.product.constant.ProductConstant;
import com.product.dto.InventoryDto;
import com.product.dto.ProductDto;
import com.product.entity.ProductEntity;
import com.product.exception.ProductNotFoundException;
import com.product.repo.ProductRepo;
import com.product.service.ProductService;

@RefreshScope
@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Value("${inventoryUrl:Config-Server is not working. Please check...}")
	private String inventoryUrl;
	// private static String inventory_serice_url = "http://inventory-service/";
	@Autowired
	ProductRepo productRepo;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "fallbackAllProducts", commandKey = "getAllProductServiceCommand")
	public List<ProductDto> getAllProducts() {
		log.info("get All products--");
		List<ProductDto> productRespList = new ArrayList<ProductDto>();
		List<ProductEntity> result = productRepo.findAll();
		log.info("result-->" + result);
		// String url = inventory_serice_url+ "inventory/getAllInventory";
		log.info("inventoryUrl :" + inventoryUrl);
		InventoryDto[] inventoryDto = restTemplate.getForObject(inventoryUrl + "getAllInventory", InventoryDto[].class);
		List<InventoryDto> responseList = Arrays.asList(inventoryDto);
		log.info("inventory response List--" + responseList);
		for (ProductEntity productEntity : result) {
			log.info("iterating productEntity result..");
			ProductDto dto = domaintToDto(productEntity);
			for (InventoryDto invDto : responseList) {
				if (dto.getProductId().equals(String.valueOf(invDto.getProductId()))) {
					dto.setProductQty(invDto.getInventoryQuantity());
				}
			}
			productRespList.add(dto);
		}
		return productRespList;
	}

	private List<ProductDto> fallbackAllProducts(){
		List<ProductDto> productRespList = new ArrayList<ProductDto>();
		List<ProductEntity> result = productRepo.findAll();
		for (ProductEntity productEntity : result) {
			log.info("iterating productEntity result..");
			ProductDto dto = domaintToDto(productEntity);
			//adding default quantity 
			dto.setProductQty(0);
			productRespList.add(dto);
		}
		return productRespList;
	}
	@Override
	@HystrixCommand(fallbackMethod = "fallbackGetProductDtls", commandKey = "getProductServiceCommand")
	public ProductDto getProductDetails(String productId) {
		log.info("get  product details--");
		ProductDto dto = new ProductDto();
		try {
			// String url = inventory_serice_url+ "inventory/getInventoryById/" + productId;
			Optional<ProductEntity> productEntity = productRepo.findById(Long.parseLong((productId)));
			log.info("Product Entity-- " + productEntity);
			dto.setProductId(String.valueOf(productEntity.get().getId()));
			dto.setProductName(productEntity.get().getName());
			dto.setProductPrice(productEntity.get().getPrice());
			log.info("Product dto-- " + dto);
			log.info("inventoryUrl------>"+inventoryUrl);
			InventoryDto invDto = restTemplate.getForObject(inventoryUrl + "getInventoryById/" + productId,
					InventoryDto.class);
			dto.setProductQty(invDto.getInventoryQuantity());
		} catch (Exception e) {
			log.error("Exception occured in get Product Details" + e.getMessage());
			throw new ProductNotFoundException(ProductConstant.notFoundException);
		}
		log.info("Product Response" + dto);
		return dto;
	}
	private ProductDto fallbackGetProductDtls(String productId) {
		ProductDto dto = new ProductDto();
		Optional<ProductEntity> productEntity = productRepo.findById(Long.parseLong((productId)));
		log.info("Product Entity-- " + productEntity);
		dto.setProductId(String.valueOf(productEntity.get().getId()));
		dto.setProductName(productEntity.get().getName());
		dto.setProductPrice(productEntity.get().getPrice());
		//Adding Default Quantity
		dto.setProductQty(0);
		return dto;
	}

	@Override
	public ProductDto saveProduct(ProductDto dto) {
		log.info("inside save product");
		ProductEntity productEntity = dtoToDomain(dto);
		// String url = inventory_serice_url+ "inventory/saveInventory";
		InventoryDto invDto = prodDtoToInvDto(dto);
		log.info("inventoryUrl--->"+inventoryUrl);
		InventoryDto inventoryResult = restTemplate.postForObject(inventoryUrl + "saveInventory", invDto,
				InventoryDto.class);
		log.info("inventoryResult-->" + inventoryResult);
		ProductDto responseDto = domaintToDto(productRepo.save(productEntity));
		responseDto.setProductQty(inventoryResult.getInventoryQuantity());
		return responseDto;
	}

	@Override
	public String deleteProduct(String id) {
		log.info("inside delete product" + id);
		if (productRepo.existsById(Long.parseLong(id))) {
			productRepo.deleteById(Long.parseLong(id));
			// String url = inventory_serice_url+ "inventory/deleteInventory/" + id;
			restTemplate.delete(inventoryUrl + "deleteInventory");
			if (productRepo.existsById(Long.parseLong(id))) {
				return "Requested Product Id Not Deleted Succssfully :" + id;
			} else {
				return "Requested Product Id Deleted Succssfully :" + id;
			}
		} else {
			return "Requested Product Id Not found :" + id;
		}
	}

	@Override
	public ProductDto updateProduct(String productId, ProductDto dto) {

		ProductEntity productEntity = new ProductEntity();
		Optional<ProductEntity> result;
		try {
			result = productRepo.findById(Long.parseLong(productId));
		} catch (Exception e) {
			throw new ProductNotFoundException(ProductConstant.notFoundException);
		}
		productEntity.setId(result.get().getId());
		productEntity.setName(dto.getProductName());
		productEntity.setPrice(dto.getProductPrice());
		ProductEntity productResult = productRepo.save(productEntity);
		// Application application = eurekaClient.getApplication("inventory-service");
		// InstanceInfo instanceInfo = application.getInstances().get(0);
		// String url = inventory_serice_url+"inventory/updateInventory/" + productId;
		InventoryDto invDto = new InventoryDto();
		invDto.setProductId(Long.parseLong(productId));
		invDto.setInventoryQuantity(dto.getProductQty());
		restTemplate.put(inventoryUrl + "updateInventory", invDto);
		ProductDto responseDto = domaintToDto(productResult);
		responseDto.setProductQty(invDto.getInventoryQuantity());
		return responseDto;

	}

	private ProductDto domaintToDto(ProductEntity productEntity) {

		ProductDto dto = new ProductDto();
		dto.setProductId(String.valueOf(productEntity.getId()));
		dto.setProductName(productEntity.getName());
		dto.setProductPrice(productEntity.getPrice());

		return dto;
	}

	private ProductEntity dtoToDomain(ProductDto dto) {

		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(Long.valueOf(dto.getProductId()));
		productEntity.setName(dto.getProductName());
		productEntity.setPrice(dto.getProductPrice());

		return productEntity;
	}

	private InventoryDto prodDtoToInvDto(ProductDto prodDto) {
log.info("prodDto--------->"+prodDto.getProductId()+"--------------->"+prodDto.getProductQty());
		InventoryDto invDto = new InventoryDto();
		invDto.setProductId(Long.parseLong(prodDto.getProductId()));
		invDto.setInventoryQuantity(prodDto.getProductQty());
		return invDto;
	}
}
