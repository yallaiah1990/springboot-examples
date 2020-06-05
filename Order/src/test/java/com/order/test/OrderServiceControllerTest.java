package com.order.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.order.dto.OrderDto;
import com.order.constant.OrderConstant;
import com.order.controller.OrderController;
import com.order.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OrderServiceControllerTest {
    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;
    List<OrderDto> listOrderDto;
    OrderDto orderDto;
    OrderDto orderDto1;

    @Before
    public void setup() {
        orderDto = new OrderDto();
        orderDto1 = new OrderDto();
        listOrderDto = new ArrayList<OrderDto>();
    }

    @Test
    public void getOrderById() {
        orderDto.setProductId(101);
        orderDto.setOrderedBy("john");
        orderDto.setOrderQuantity(2);
        when(orderService.getOrderDetails(101)).thenReturn(orderDto);
        orderDto = orderController.getOrderDetails(101);

    }

    @Test
    public void getAllOrders() {
        orderDto.setProductId(101);
        orderDto.setOrderedBy("john");
        orderDto.setOrderQuantity(2);
        orderDto1.setProductId(102);
        orderDto1.setOrderedBy("lizzy");
        orderDto1.setOrderQuantity(3);
        listOrderDto.add(orderDto);
        listOrderDto.add(orderDto1);
        when(orderService.getAllOrder()).thenReturn(listOrderDto);
        listOrderDto = orderController.getAllOrder();

    }

    @Test
    public void saveProduct() {
        orderDto.setProductId(101);
        orderDto.setOrderedBy("john");
        orderDto.setOrderQuantity(2);
        when(orderService.saveOrder(orderDto)).thenReturn(OrderConstant.sucessMsg);
        String mesg = orderController.saveOrder(orderDto);
    }

    @Test
    public void deleteOrder() {
        when(orderService.deleteOrder(101)).thenReturn("deleted");
        String result = orderController.deleteOrder(101);

    }
}
