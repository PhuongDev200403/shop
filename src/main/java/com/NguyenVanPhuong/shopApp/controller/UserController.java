package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.UserCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.UserLoginRequest;
import com.NguyenVanPhuong.shopApp.entity.User;
import com.NguyenVanPhuong.shopApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ApiResponse<?> createUser(
            @Valid @RequestBody UserCreateRequest request,
            BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<?> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();
                return ApiResponse.builder()
                        .success(false)
                        .message("Tạo người dùng thất bại")
                        .result(errorMessage)
                        .build();
            }
            return ApiResponse.builder()
                    .success(true)
                    .result(userService.createUser(request))
                    .build();
        } catch (Exception e) {
            return ApiResponse.builder()
                    .success(false)
                    .result(e.getMessage())
                    .build();
        }
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody UserLoginRequest request){
        String token = userService.login(request);
        return ApiResponse.<String>builder()
                .success(true)
                .message("Lấy token thành công")
                .result(token)
                .build();
    }
}
