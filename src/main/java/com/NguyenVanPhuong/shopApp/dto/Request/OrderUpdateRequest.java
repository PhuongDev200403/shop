package com.NguyenVanPhuong.shopApp.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateRequest {
    @JsonProperty("email")
    String email;
    @JsonProperty("phone_number")
    String phoneNumber;
    @JsonProperty("note")
    String note;
    @Min(value = 1, message = "Total money must be greater than 1")
    @JsonProperty("total_money")
    float totalMoney;
    @JsonProperty("shipping_method")
    String shippingMethod;
    @JsonProperty("shipping_address")
    String shippingAddress;
    @JsonProperty("payment_method")
    String paymentMethod;
}
