package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name ="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "fullname", length = 100)
    String fullName;
    @Column(name = "email", length = 150)
    String email;
    @Column(name = "phone_number", length = 10, nullable = false)
    String phoneNumber;
    @Column(name = "address")
    String address;
    String note;
    @Column(name = "order_date")
    LocalDateTime orderDate;
    @Column(name = "status")
    String status;
    @Column(name = "total_money")
    Float totalMoney;
    @Column(name = "shipping_method")
    String shippingMethod;
    @Column(name = "shipping_address")
    String shippingAddress;
    @Column(name = "shipping_date")
    Date shippingDate;
    @Column(name = "tracking_number")
    String trackingNumber;
    @Column(name = "payment_method")
    String paymentMethod;
    @Column(name = "active")
    Boolean active;
}
