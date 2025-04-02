package com.NguyenVanPhuong.shopApp.repository;

import com.NguyenVanPhuong.shopApp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
=======
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
>>>>>>> ac81d0052690a7b2af49999db6ebf632ebfed85a
}
