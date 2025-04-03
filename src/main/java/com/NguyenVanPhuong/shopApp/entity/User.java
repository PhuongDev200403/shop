package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity(name ="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "fullname", length = 100)
    String fullName;
    @Column(name = "phone_number", length = 10, nullable = false)
    String phoneNumber;
    @Column(name = "address", length = 200)
    String address;
    @Column(name = "password", length = 200)
    String password;
    Boolean active;
    @Column(name = "date_of_birth")
    Date dateOfBirth;
    @Column(name = "facebook_account_id")
    Long facebookAccountId;
    @Column(name = "google_account_id")
    Long googleAccountId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
