package com.NguyenVanPhuong.shopApp.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse{
    Long id; // mã đơn hàng
    @JsonProperty("user_id") // id của người đặt hàng
    Long userId;
    @JsonProperty("fullname") //Tên của người đặt hàng
    String fullName;
    @JsonProperty("email")
    String email;
    @JsonProperty("phone_number") // email của người đặt hàng
    String phoneNumber;
    @JsonProperty("address")
    String address;
    @JsonProperty("note")
    String note;
    @JsonProperty("order_date")
    LocalDateTime orderDate;
    @JsonProperty("status")
    String status;
    @JsonProperty("total_money")
    float totalMoney;
    @JsonProperty("shipping_date")
    LocalDate shippingDate;
    @JsonProperty("tracking_number")
    String trackingNumber;
    @JsonProperty("shipping_method")
    String shippingMethod;
    @JsonProperty("shipping_address")
    String shippingAddress;
    @JsonProperty("payment_method")
    String paymentMethod;
}
