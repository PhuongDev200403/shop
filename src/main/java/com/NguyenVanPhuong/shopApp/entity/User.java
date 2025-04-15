package com.NguyenVanPhuong.shopApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name ="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {
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
    @Column(name = "is_active")
    Boolean active;
    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;
    @Column(name = "facebook_account_id")
    Long facebookAccountId;
    @Column(name = "google_account_id")
    Long googleAccountId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + getRole().getName()));
        //authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
