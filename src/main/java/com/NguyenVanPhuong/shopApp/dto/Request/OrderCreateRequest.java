package com.NguyenVanPhuong.shopApp.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
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
public class OrderCreateRequest {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User id must be greater than 1")
    Long userId;
    @NotBlank(message = "Fullname not be empty")
    @JsonProperty("fullname")
    String fullName;
    String email;
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number invalid")
    @JsonProperty("phone_number")
    String phoneNumber;
    @NotBlank(message = "Address not be empty")
    String address;
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
