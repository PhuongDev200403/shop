package com.NguyenVanPhuong.shopApp.mapper;

import com.NguyenVanPhuong.shopApp.dto.Request.OrderDetailCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderDetailUpdateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.OrderDetailResponse;
import com.NguyenVanPhuong.shopApp.entity.Order;
import com.NguyenVanPhuong.shopApp.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderDetail toOrderDetail(OrderDetailCreateRequest request);

    //@Mapping(source = "order", target = "order")
    //@Mapping(source = "product", target = "product")
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

    void updateOrderDetail(@MappingTarget OrderDetail orderDetail, OrderDetailUpdateRequest request);
}
