package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name ="social_accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "provider", length = 20, nullable = false)
    String provider;
    @Column(name = "provider_id", length = 50, nullable = false)
    String providerId;
    @Column(name = "email", length = 150)
    String email;
    @Column(name = "name", length = 150)
    String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
