package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.OrderCreateRequest;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping()
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderCreateRequest request,
            BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Order created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Lấy ra ọt order theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderByID(@Valid @PathVariable Long id){
        try{
            return ResponseEntity.ok("Danh sach tất cả các order theo user_id");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Cập nhật order theo id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @Valid @PathVariable Long id,
            @RequestBody OrderCreateRequest request,
            BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<?> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getDefaultMessage())
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Cập nhật thành công");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Xóa order
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@Valid @PathVariable Long id){
        return ResponseEntity.ok("Xóa thành công");
    }
}
