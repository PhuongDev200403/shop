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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ApiResponse<?> createUser(@Valid @RequestBody UserCreateRequest request){
        return ApiResponse.builder()
                .result(userService.createUser(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody UserLoginRequest request, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ApiResponse.<String>builder()
                    .message("Validation error: " + errors)
                    .build();
        }

        try {
            String token = userService.login(request);
            return ApiResponse.<String>builder()
                    .message("Lấy token thành công")
                    .result(token)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .message("Error: " + e.getMessage())
                    .build();
        }
    }

}
