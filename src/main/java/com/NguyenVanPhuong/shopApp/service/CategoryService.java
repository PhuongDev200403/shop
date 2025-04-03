package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.dto.Request.CategoryCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.CategoryUpdateRequest;
import com.NguyenVanPhuong.shopApp.entity.Category;
import com.NguyenVanPhuong.shopApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    //tạo mới một danh mục sản phẩm
    public Category createCategory(CategoryCreateRequest request){
        Category category = Category.builder()
                .name(request.getName())
                .build();
        return categoryRepository.save(category);
    }
    //Timf theo id
    public Category getCategoryById(long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Danh mục không tồn tại"));
    }

    //Lấy tất cả danh các danh mục
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    //Cập nhật một danh mục
    public Category updateCategory(long id, CategoryUpdateRequest request){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Danh mục không tồn tại"));
        category.setName(request.getName());
        return categoryRepository.save(category);
    }
    //Xóa một danh mục
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

}
