package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
}
