package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "token", length = 255, nullable = false)
    String token;
    @Column(name = "token_type", length = 50)
    String tokenType;
    Long revoked;
    Long expired;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
