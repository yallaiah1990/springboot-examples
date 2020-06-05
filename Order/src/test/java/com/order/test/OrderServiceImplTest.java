package com.order.test;

import com.order.dto.OrderDto;
import com.order.entity.OrderEntity;
import com.order.repo.OrderRepo;
import com.order.serviceImpl.OrderServiceImpl;
import com.order.util.Utility;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@TestPropertySource(locations="classpath:application-test.properties")
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepo orderRepo;
    @Mock
    Utility utility;
    @Mock
    ModelMapper modelMapper;
    public OrderDto orderDTO = new OrderDto();
    public OrderEntity orderEntity = new OrderEntity(1,2,"Mary");


    @Before
    public void setUp() {
        when(modelMapper.map(any(), any())).thenReturn(orderDTO);
        MockitoAnnotations.initMocks(this);
        orderDTO.setOrderId(1);
        orderDTO.setProductId(1);
        orderDTO.setOrderQuantity(2);
        orderDTO.setOrderedBy("Mary");
    }

    @Test
    public void testGetOrderDetails() throws Throwable {
        when(orderRepo.getOne(anyInt())).thenReturn(orderEntity);
        when(orderService.getOrderDetails(anyInt())).thenReturn(orderDTO);
        OrderDto result = orderService.getOrderDetails(1);
        System.out.println(result);
        assertNotNull(result);

    }

    @Test
    public void testSaveOrder() throws Exception {
        when(orderRepo.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderService.saveOrder(any(OrderDto.class))).thenReturn("Order Placed successfully");
        String succMes = orderService.saveOrder(orderDTO);
        assertEquals(succMes,"Order Placed successfully");
    }

 }



