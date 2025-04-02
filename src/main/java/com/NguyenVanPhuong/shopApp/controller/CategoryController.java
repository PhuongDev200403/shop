package com.NguyenVanPhuong.shopApp.controller;

//import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.CategoryCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.CategoryUpdateRequest;
//import com.NguyenVanPhuong.shopApp.dto.Response.CategoryResponse;
//import com.NguyenVanPhuong.shopApp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CategoryController {

//    @Autowired
//    CategoryService categoryService;

//    @PostMapping("/categories")
//    public ApiResponse<CategoryResponse> create(@RequestBody @Valid CategoryCreateRequest request){
//        return ApiResponse.<CategoryResponse>builder()
//                .result(categoryService.createCategory(request))
//                .build();
//    }
//    @GetMapping("/categories")
//    public ApiResponse<List<CategoryResponse>> getAll(){
//        return ApiResponse.<List<CategoryResponse>>builder()
//                .result(categoryService.getAll())
//                .build();
//    }
//    @PutMapping("/categories/{id}")
//    public ApiResponse<CategoryResponse> update(@PathVariable int id, @RequestBody CategoryUpdateRequest request){
//        return  ApiResponse.<CategoryResponse>builder()
//                .result(categoryService.updateCategory(id, request))
//                .build();
//    }
//    @DeleteMapping("/categories/{id}")
//    public ApiResponse<Void> delete(@PathVariable int id){
//        categoryService.deleteCategory(id);
//        return ApiResponse.<Void>builder().build();
//    }

    @GetMapping("/categories")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok("getAllCategories");
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCate(
            @RequestBody @Valid CategoryCreateRequest request,
            BindingResult result
    ){

        if(result.hasErrors()){
           List<String> errorMessages = result.getFieldErrors()
                   .stream()
                   .map(DefaultMessageSourceResolvable::getDefaultMessage)
                   .toList();
           return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("create a category: " + request);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<String> getById(@PathVariable int id){
        return ResponseEntity.ok("product id :" +id);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }
}
