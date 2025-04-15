package com.NguyenVanPhuong.shopApp.mapper;

import com.NguyenVanPhuong.shopApp.dto.Request.OrderCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderUpdateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.OrderResponse;
import com.NguyenVanPhuong.shopApp.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "user", ignore = true)
    Order toOrder(OrderCreateRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.address", target = "address")
    OrderResponse toOrderResponse(Order order);

    //@Mapping(target = "user", ignore = true)
    void updateOrder(@MappingTarget Order order, OrderUpdateRequest request);
}
