package com.NguyenVanPhuong.shopApp.repository;

import com.NguyenVanPhuong.shopApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface UserReposiory extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByPhoneNumber(String phoneNumber);
=======
@Repository
public interface UserReposiory extends JpaRepository<User, Long> {
>>>>>>> ac81d0052690a7b2af49999db6ebf632ebfed85a
}
