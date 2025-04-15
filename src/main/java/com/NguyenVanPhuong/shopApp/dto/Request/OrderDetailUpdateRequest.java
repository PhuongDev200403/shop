package com.NguyenVanPhuong.shopApp.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailUpdateRequest {
    //không cập nhật các thông tin như order_id và product_id
//    @JsonProperty("order_id")
//    Long orderId;
//    //Order order;
//    @JsonProperty("product_id")
//    Long productId;
//    //Product product;
    float price;
    @Min(value = 1, message = "Sô lượng sản phẩm phải lớn hơn 1")
    @JsonProperty("number_of_product")
    int numberOfProduct;
    @JsonProperty("total_money")
    float totalMoney;
    String color;
}
