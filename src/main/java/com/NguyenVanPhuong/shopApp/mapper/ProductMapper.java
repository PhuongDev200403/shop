package com.NguyenVanPhuong.shopApp.mapper;

import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.ProductResponse;
import com.NguyenVanPhuong.shopApp.entity.Category;
import com.NguyenVanPhuong.shopApp.entity.Product;
import jakarta.validation.groups.Default;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default Product toProduct(ProductCreateRequest request) {
        if (request == null) return null;

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        // Set category nếu có category_id
        if (request.getCategory_id() > 0) {
            Category category = new Category();
            category.setId(request.getCategory_id());
            product.setCategory(category);
        }

        return product;
    }

    @Mapping(source = "category.id", target = "category_id")
    ProductResponse toProductResponse(Product product);
}
