package com.NguyenVanPhuong.shopApp.repository;

import com.NguyenVanPhuong.shopApp.entity.Product;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ProductReposiory extends JpaRepository<Product, Long> {
    Boolean existsByName(String name);
    Page<Product> findAll(Pageable pageable); // phân trang
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReposiory extends JpaRepository<Product, Long> {
>>>>>>> ac81d0052690a7b2af49999db6ebf632ebfed85a
}
