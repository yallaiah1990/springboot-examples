package com.order.util;

import com.order.dto.OrderDto;
import com.order.entity.OrderEntity;
import org.modelmapper.ModelMapper;

public interface Utility {

    ModelMapper modelMapper = new ModelMapper();

    public static OrderDto convertEntityToResponse(OrderEntity orderEntity){
    return modelMapper.map(orderEntity, OrderDto.class);
    }
}
