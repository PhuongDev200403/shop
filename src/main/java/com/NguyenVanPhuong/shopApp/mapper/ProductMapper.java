package com.NguyenVanPhuong.shopApp.mapper;

import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.ProductResponse;
import com.NguyenVanPhuong.shopApp.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreateRequest request);
    ProductResponse toProductResponse(Product product);
}
