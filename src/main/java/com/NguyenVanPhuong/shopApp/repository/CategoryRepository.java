package com.NguyenVanPhuong.shopApp.repository;

import com.NguyenVanPhuong.shopApp.entity.Category;
<<<<<<< HEAD
import com.NguyenVanPhuong.shopApp.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //Kiểm tra xem tên danh mục có bị trùng không khi thêm mới
    boolean existsByName(String name);
    //Tìm các sản phẩm theo id của đơn hàng đc order
    List<OrderDetail> findByOrderId(Long orderId);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
>>>>>>> ac81d0052690a7b2af49999db6ebf632ebfed85a
}
