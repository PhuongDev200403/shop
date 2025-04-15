package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderDetailCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderDetailUpdateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.OrderDetailResponse;
import com.NguyenVanPhuong.shopApp.service.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping()
    public ApiResponse<OrderDetailResponse> createOrderDetail(
            @Valid @RequestBody OrderDetailCreateRequest request
    ){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.createOrderDetail(request))
                .build();
    }
    //Lấy ra ọt order theo id
    @GetMapping("/{id}")
    public ApiResponse<OrderDetailResponse> getOrderDetailByID(@Valid @PathVariable("id") Long id){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.getOrderDetailById(id))
                .build();
    }
    //Lấy tất cả chi tiết
    @GetMapping("/order/{order_id}")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetai(@Valid @PathVariable("order_id") Long orderId){
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetailsByOrder(orderId))
                .build();
    }
    //Cập nhật order theo id
    @PutMapping("/{id}")
    public ApiResponse<OrderDetailResponse> updateOrder(
            @Valid @PathVariable Long id,
            @RequestBody OrderDetailUpdateRequest request
            //BindingResult result
    ){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateOrderDetail(id, request))
                .build();
    }

    //Xóa order

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteOrder(@Valid @PathVariable("id") Long id){
        orderDetailService.deleteOrderDetailById(id);
        return ApiResponse.<String>builder()
                .result("delete successfully")
                .build();
    }

}
