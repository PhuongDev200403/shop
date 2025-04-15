package com.NguyenVanPhuong.shopApp.controller;

//import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.CategoryCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.CategoryUpdateRequest;
//import com.NguyenVanPhuong.shopApp.dto.Response.CategoryResponse;
//import com.NguyenVanPhuong.shopApp.service.CategoryService;
import com.NguyenVanPhuong.shopApp.entity.Category;
import com.NguyenVanPhuong.shopApp.repository.CategoryRepository;
import com.NguyenVanPhuong.shopApp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')") //chỉ admin mơi có quyền tạo danh mục
    public ApiResponse<?> createCategory(
            @RequestBody @Valid CategoryCreateRequest request
    ){
        return ApiResponse.builder()
                .message("tạo danh mục mới thành công")
                .result(categoryService.createCategory(request))
                .build();
    }
    @GetMapping("/categories")
    public ApiResponse<List<Category>> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<Category> categories = categoryService.getAllCategories();
        return ApiResponse.<List<Category>>builder()
                .result(categories)
                .build();
    }

    @GetMapping("/categories/{id}")
    public ApiResponse<Category> getById(@PathVariable long id){
        Category category = categoryService.getCategoryById(id);
        return ApiResponse.<Category>builder()
                .result(category)
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy danh mục tương úng"));
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/categories/{id}")
    public ApiResponse<Category> updateCategory(@PathVariable long id, @RequestBody CategoryUpdateRequest request){
        Category category = categoryService.updateCategory(id, request);
        return ApiResponse.<Category>builder()

                .result(category)
                .build();
    }
}
