package com.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inventory.InventoryDetailsApplication;
import com.inventory.dto.InventoryDto;
import com.inventory.entity.InventoryEntity;
import com.inventory.repo.InventoryRepo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryDetailsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations="classpath:application-test.properties")
public class InventoryIntegration {
	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		RestAssured.port = this.port;
	}

	@MockBean
	private InventoryRepo inventoryRepo;
	//private static final ObjectMapper om = new ObjectMapper();
	@Test
	public void getAllInventoryTest() {
		List<InventoryEntity> inventory = Arrays.asList(new InventoryEntity(101L, 3), new InventoryEntity(102L, 5));

		when(inventoryRepo.findAll()).thenReturn(inventory);
		String body = RestAssured.given().when().get("/inventory/getAllInventory").then().contentType(ContentType.JSON)
				.extract().asString();
		System.out.println("--------" + body);
		assertThat(!(body.isEmpty()));
	}

	@Test
	public void getInventoryDetails() throws NumberFormatException, JSONException {
		Optional<InventoryEntity> optionalInventoryEntity = Optional.of(new InventoryEntity(101L, 3));
		when(inventoryRepo.findById(Long.parseLong("101"))).thenReturn(optionalInventoryEntity);

		InventoryDto body = RestAssured.given().when().get("/inventory/getInventoryById/{id}", 101).then().extract()
				.as(InventoryDto.class);
		assertThat(body.getInventoryQuantity() != null);
		assertThat(body.toString().contains("productId"));
		assertEquals("101", body.getProductId().toString());
	}

	@Test
	public void deleteInventoryDetails() throws NumberFormatException, JSONException {
		doNothing().when(inventoryRepo).deleteById(101L);

		String body = RestAssured.given().when().delete("/inventory/deleteInventory/{id}", 101).then().extract()
				.asString();
		assertThat(body.getBytes().toString().contains("Deleted Succssfully"));
	}

	@Test
	public void saveInventoryDetails() throws NumberFormatException, JSONException, JsonProcessingException {
		InventoryEntity inventoryEntity = new InventoryEntity(101L, 3);
		when(inventoryRepo.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);

		String data = "{\"productId\":\"101\",\"orderQuantity\":\"3\"}";

		String body = RestAssured.given().when().body(data).post("/inventory/saveInventory").then().extract()
				.asString();
		// assertThat(body.getBytes().toString().contains("productId"));
	}

	@Test
	public void updateInventoryDetails() throws NumberFormatException, JSONException, JsonProcessingException {
		InventoryEntity inventoryEntity = new InventoryEntity(101L, 3);
		when(inventoryRepo.save(any(InventoryEntity.class))).thenReturn(inventoryEntity);

		String data = "{\"productId\":\"101L\",\"orderQuantity\":\"5\"}";

		String body = RestAssured.given().when().body(data).put("/inventory/updateInventory/{id}", 101L).then()
				.extract().asString();
		// assertEquals("5",body.getBytes().toString().contains("5"));

	}

	/*
	 * @Test public void deleteInventoryDetailsNotFound() {
	 * 
	 * String body =
	 * RestAssured.given().when().delete("/inventory/deleteInventory/{id}",
	 * 1).then().extract() .asString();
	 * assertThat(body.getBytes().toString().contains("Inventory Id Not found"));
	 * 
	 * }
	 */

}
