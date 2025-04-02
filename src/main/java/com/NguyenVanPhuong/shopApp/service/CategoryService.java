//package com.NguyenVanPhuong.shopApp.service;
//
//import com.NguyenVanPhuong.shopApp.dto.Request.CategoryCreateRequest;
//import com.NguyenVanPhuong.shopApp.dto.Request.CategoryUpdateRequest;
//import com.NguyenVanPhuong.shopApp.dto.Response.CategoryResponse;
//import com.NguyenVanPhuong.shopApp.entity.Category;
//import com.NguyenVanPhuong.shopApp.exception.AppException;
//import com.NguyenVanPhuong.shopApp.exception.ErrorCode;
//import com.NguyenVanPhuong.shopApp.mapper.CategoryMapper;
//import com.NguyenVanPhuong.shopApp.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CategoryService {
//    @Autowired
//    CategoryRepository categoryRepository;
//    @Autowired
//    CategoryMapper categoryMapper;
//    //tạo mới một danh mục
//    public CategoryResponse createCategory(CategoryCreateRequest request){
//        if(categoryRepository.existsByName(request.getName())){
//            throw new AppException(ErrorCode.CATEGORY_EXISTED);
//        }
//        Category category = categoryMapper.toCategory(request);
//        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
//    }
//    //sửa một danh mục
//    public CategoryResponse updateCategory(int id ,CategoryUpdateRequest request){
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
//        category.setName(request.getName());
//        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
//    }
//    //lấy tất cả danh mục
//    public List<CategoryResponse> getAll(){
//        var categories = categoryRepository.findAll();
//        return categories.stream().map(categoryMapper::toCategoryResponse).toList();
//    }
//    //Lấy theo id
//    public CategoryResponse getById(int id){
//
//        return categoryMapper.toCategoryResponse(categoryRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED)));
//    }
//    //Xóa 2 danh mục
//    public void deleteCategory(int id){
//        categoryRepository.deleteById(id);
//    }
//}
