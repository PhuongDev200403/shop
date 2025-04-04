package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    Float price;
    @Column(name = "url")
    String url;
    @Column(name = "description")
    String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category ;
}
