package com.NguyenVanPhuong.shopApp.repository;

import com.NguyenVanPhuong.shopApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
