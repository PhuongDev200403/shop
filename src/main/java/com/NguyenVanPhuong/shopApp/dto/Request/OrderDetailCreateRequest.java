package com.NguyenVanPhuong.shopApp.dto.Request;

import com.NguyenVanPhuong.shopApp.entity.Order;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreateRequest {
    @JsonProperty("order_id")
    Long orderId;
    //Order order;
    @JsonProperty("product_id")
    Long productId;
    //Product product;
    float price;
    @Min(value = 1, message = "Sô lượng sản phẩm phải lớn hơn 1")
    @JsonProperty("number_of_product")
    int numberOfProduct;
    @JsonProperty("total_money")
    float totalMoney;
    String color;
}
