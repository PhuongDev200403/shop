package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.dto.Request.OrderCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderUpdateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.OrderResponse;
import com.NguyenVanPhuong.shopApp.entity.Order;
import com.NguyenVanPhuong.shopApp.entity.OrderStatus;
import com.NguyenVanPhuong.shopApp.entity.User;
import com.NguyenVanPhuong.shopApp.exception.AppException;
import com.NguyenVanPhuong.shopApp.exception.ErrorCode;
import com.NguyenVanPhuong.shopApp.mapper.OrderMapper;
import com.NguyenVanPhuong.shopApp.repository.OrderRepository;
import com.NguyenVanPhuong.shopApp.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserReposiory userReposiory;

    @Autowired
    OrderMapper orderMapper;

    //tạo một đơn hàng mới bằng orderMapper
    public OrderResponse createOrder(OrderCreateRequest request){
        User user = userReposiory.findById(request.getUserId()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        Order order = orderMapper.toOrder(request);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        LocalDate shippingDate = request.getShippingDate() == null ? LocalDate.now() : request.getShippingDate();
        if(shippingDate.isBefore(LocalDate.now())){
            throw new AppException(ErrorCode.DATE_INVALID);
        }
        if(order.getFullName() == null || order.getFullName().isBlank()){
            order.setFullName(user.getFullName());
        }
        if (order.getAddress() == null || order.getAddress().isBlank()) {
            order.setAddress(user.getAddress());
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }
    //Lấy tất cả Order ra thông qua user id
    public List<OrderResponse> getAllByUserId(Long userId){

        return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toOrderResponse).toList();
    }
    //Lấy tất cả order trong bảng order
    public List<OrderResponse> getAllOrder(){
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse).toList();
    }
    //Lấy ra một order theo id của order
    public OrderResponse getOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        return orderMapper.toOrderResponse(order);
    }
    //Cập nhật một Order
    public OrderResponse updateOrder(Long id, OrderUpdateRequest request){
        //Kiểm tra xem id có tồn tại hay không
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        orderMapper.updateOrder(order, request);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }
    //Xóa một Order
    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        order.setActive(false);
        orderRepository.save(order);
    }

}
