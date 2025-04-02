package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "order_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @Min(value= 0, message = "Giá phải lớn hơn 0")
    Float price;
    @Min(value = 1, message = "sản phầm phải lơn hơn 1")
    Long numberOfProduct;
    @Column(name = "total_money")
    @Min(value = 0, message = "Tổng giá phải lớn hơn 0")
    Float totalMoney;
    String color;
}
