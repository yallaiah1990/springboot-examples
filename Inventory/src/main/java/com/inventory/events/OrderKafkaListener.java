package com.inventory.events;

import com.inventory.dto.InventoryDto;
import com.inventory.entity.OrderEntity;
import com.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaListener {

    private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);

    private InventoryService inventoryService;

    public OrderKafkaListener(InventoryService inventoryService) {
        super();
        this.inventoryService = inventoryService;
    }


    @KafkaListener(topics = "order",groupId = "OrderSystem",containerFactory = "kafkaListener")
    public void order(OrderEntity orderEntity, Acknowledgment acknowledgment) {
        log.info("Received order for id  " + orderEntity.getProductId());
        InventoryDto inventoryResponse = inventoryService.getInventoryById(orderEntity.getProductId());
        InventoryDto inventoryRequest= new InventoryDto(orderEntity.getProductId(), (int) (inventoryResponse.getInventoryQuantity()-orderEntity.getOrderQuantity()));
        inventoryService.updateInventory(orderEntity.getProductId(),inventoryRequest);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "cancel",groupId = "OrderSystem",containerFactory = "kafkaListener")
    public void cancel(OrderEntity oderEntity, Acknowledgment acknowledgment) {
        log.info("Cancelled order for id  " + oderEntity.getProductId());
        InventoryDto inventoryResponse = inventoryService.getInventoryById(oderEntity.getProductId());
        InventoryDto inventoryRequest= new InventoryDto(oderEntity.getProductId(), Math.toIntExact((inventoryResponse.getInventoryQuantity() + oderEntity.getOrderQuantity())));
        inventoryService.updateInventory(oderEntity.getProductId(),inventoryRequest);
        acknowledgment.acknowledge();
    }

}

