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
public class OrderCreateRequest {
    @JsonProperty("user_id")
    @Min(value = 1, message = "INVALID_ID")
    Long userId;
//    @NotBlank(message = "FULLNAME_NOT_EMPTY")
//    @JsonProperty("fullname")
//    String fullName;
    @JsonProperty("email")
    String email;
//    @NotBlank(message = "")
//    @Size(min = 10, max = 10, message = "PHONE_NUMBER_INVALID")
//    @JsonProperty("phone_number")
//    String phoneNumber;
//    @NotBlank(message = "ADDRESS_NOT_EMPTY")
//    @JsonProperty("address")
//    String address;
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
    @JsonProperty("shipping_date")
    LocalDate shippingDate;
}
