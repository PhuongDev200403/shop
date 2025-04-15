package com.NguyenVanPhuong.shopApp.dto.Response;

import com.NguyenVanPhuong.shopApp.entity.Order;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    Long id;
//    Long orderId;
//    Long productId;
    Order order;
    Product product;
    float price;
    int numberOfProduct;
    float totalMoney;
    String color;
}
