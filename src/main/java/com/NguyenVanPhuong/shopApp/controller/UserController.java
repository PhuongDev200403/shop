package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.UserCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.UserLoginRequest;
import jakarta.validation.Valid;
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

    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserCreateRequest request,
            BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<?> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Register successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest request){
        return ResponseEntity.ok("Token here");
    }
}
