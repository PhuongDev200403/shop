package com.NguyenVanPhuong.shopApp.mapper;

import com.NguyenVanPhuong.shopApp.dto.Request.CategoryCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.CategoryResponse;
import com.NguyenVanPhuong.shopApp.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreateRequest request);
    CategoryResponse toCategoryResponse(Category category);
}
