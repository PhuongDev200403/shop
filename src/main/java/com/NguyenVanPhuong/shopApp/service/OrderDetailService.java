package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.dto.Request.OrderDetailCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderDetailUpdateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.OrderDetailResponse;
import com.NguyenVanPhuong.shopApp.entity.Order;
import com.NguyenVanPhuong.shopApp.entity.OrderDetail;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.NguyenVanPhuong.shopApp.exception.AppException;
import com.NguyenVanPhuong.shopApp.exception.ErrorCode;
import com.NguyenVanPhuong.shopApp.mapper.OrderDetailMapper;
import com.NguyenVanPhuong.shopApp.repository.OrderDetailRepository;
import com.NguyenVanPhuong.shopApp.repository.OrderRepository;
import com.NguyenVanPhuong.shopApp.repository.ProductReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductReposiory productReposiory;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    //tạo một chi tiết đơn hàng
    public OrderDetailResponse createOrderDetail(OrderDetailCreateRequest request){
        Order order = orderRepository.findById(
                request.getOrderId()
                )
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        Product product = productReposiory.findById(
                request.getProductId()
                )
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(request);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
    }

    //Lấy ra một chi tiết đơn hàng
    public OrderDetailResponse getOrderDetailById(Long id){
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXISTED));
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    //Lấy ra danh sách các order detail của một order
    public List<OrderDetailResponse> getOrderDetailsByOrder(Long orderId){
        orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        return orderDetails.stream().map(orderDetailMapper::toOrderDetailResponse).toList();
    }
    //Chưa có cập nhật đơn hàng
    public OrderDetailResponse  updateOrderDetail(Long id, OrderDetailUpdateRequest request){
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXISTED));
        orderRepository.findById(orderDetail.getOrder().getId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        productReposiory.findById(orderDetail.getProduct().getId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        orderDetailMapper.updateOrderDetail(orderDetail, request);
        return orderDetailMapper.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
    }
    //Xóa một order detail bằng id
    public void deleteOrderDetailById(Long id){
        orderDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXISTED));
        orderDetailRepository.deleteById(id);
    }

}
