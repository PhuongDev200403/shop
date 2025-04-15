package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.OrderUpdateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.OrderResponse;
import com.NguyenVanPhuong.shopApp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping()
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request){
            return ApiResponse.<OrderResponse>builder()
                    .result(orderService.createOrder(request))
                    .build();
    }
    //Lấy ra ọt order theo id
    @GetMapping("/{id}")
    //chỉ cho phép lấy ra các đơn hàng của mình
    public ApiResponse<OrderResponse> getOrderByID(@Valid @PathVariable Long id){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrderById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable  @Valid Long id, @RequestBody OrderUpdateRequest request){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(id, request))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ApiResponse<List<OrderResponse>> getAllOrder(){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrder())
                .build();
    }
    //Lấy danh sách order theo user id
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<OrderResponse>> getAllByUserId(@PathVariable Long id){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllByUserId(id))
                .build();
    }
    //Xóa order
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> deleteOrder(@Valid @PathVariable Long id){
        orderService.deleteOrder(id);
        return ApiResponse.<String>builder()
                .result("delete successfully")
                .build();
    }
}
