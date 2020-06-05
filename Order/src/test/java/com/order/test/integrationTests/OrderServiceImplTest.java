package com.order.test.integrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.dto.OrderDto;
import com.order.entity.OrderEntity;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(partitions = 1, topics = { "order" })
public class OrderServiceImplTest {

    private static final String CREATE_ORDER_TOPIC = "order";
    private static final String CANCEL_ORDER_TOPIC = "cancel";

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;
    public OrderEntity orderEntity;
    ObjectMapper mapper = new ObjectMapper();

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @Before
    public void setup(){
         orderEntity = new OrderEntity(1,2,"keerthi");
    }

    @Test
    public void testCreateOrder() throws JsonProcessingException {
        Consumer<String,OrderEntity> consumer = configureConsumer();
        Producer<String,OrderEntity> producer = configureProducer();

        producer.send(new ProducerRecord(CREATE_ORDER_TOPIC, 02, orderEntity));

        ConsumerRecord<String,OrderEntity> singleRecord = KafkaTestUtils.getSingleRecord(consumer, CREATE_ORDER_TOPIC);
        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.value()).isEqualTo(mapper.writeValueAsString(orderEntity));

        consumer.close();
        producer.close();
    }

    @Test
    public void testCancelOrder() throws JsonProcessingException {
        Consumer<String,OrderEntity> consumer = configureConsumer();
        Producer<String,OrderEntity> producer = configureProducer();

        producer.send(new ProducerRecord(CANCEL_ORDER_TOPIC, 01, orderEntity));

        ConsumerRecord<String,OrderEntity> singleRecord = KafkaTestUtils.getSingleRecord(consumer, CANCEL_ORDER_TOPIC);
        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.value()).isEqualTo(mapper.writeValueAsString(orderEntity));

        consumer.close();
        producer.close();
    }

    private Consumer<String, OrderEntity> configureConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        Consumer<String, OrderEntity> consumer = new DefaultKafkaConsumerFactory<String, OrderEntity>(consumerProps)
                .createConsumer();
        consumer.subscribe(Arrays.asList(CANCEL_ORDER_TOPIC,CREATE_ORDER_TOPIC));
        return consumer;
    }

    private Producer<String, OrderEntity> configureProducer() {
        Map<String, Object> producerProps = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, OrderEntity>(producerProps).createProducer();
    }

    @Test
    public void testRetrieveOrder() throws Exception {
        HttpEntity<OrderDto> entity = new HttpEntity<OrderDto>(null, headers);
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                createURLWithPort("order/getOrderDetails/1"), HttpMethod.GET, entity, OrderDto.class);
        String expected = "{\"productId\":\"1\",\"orderQuantity\":\"2\",\"orderedBy\":\"keerthi\"}";
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody().toString().contains("{\"productId\":\"1\"}"));
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testUpdateOrder() throws Exception {
        OrderEntity updateEntity = new OrderEntity(3,1,"keerthi.ng");
        HttpEntity<OrderEntity> entity = new HttpEntity<OrderEntity>(updateEntity, headers);
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                createURLWithPort("order/updateOrder/1"),HttpMethod.PUT, entity, OrderDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody().toString().contains("{\"orderStatus\":\"Updated\"}"));
    }


}

