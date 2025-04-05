package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.ProductImageCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.ProductResponse;
import com.NguyenVanPhuong.shopApp.entity.Category;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.NguyenVanPhuong.shopApp.entity.ProductImage;
import com.NguyenVanPhuong.shopApp.exception.AppException;
import com.NguyenVanPhuong.shopApp.exception.ErrorCode;
import com.NguyenVanPhuong.shopApp.mapper.ProductMapper;
import com.NguyenVanPhuong.shopApp.repository.CategoryRepository;
import com.NguyenVanPhuong.shopApp.repository.ProductImageRepository;
import com.NguyenVanPhuong.shopApp.repository.ProductReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductReposiory productReposiory;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductMapper productMapper;
    //Tạo sản phẩm mới
    public Product createProduct(ProductCreateRequest request){
        Category category = categoryRepository
                .findById((long)(request.getCategory_id()))
                .orElseThrow(() ->
                        new DataIntegrityViolationException(
                                "Không tìm thấy danh mục chứa sản phẩm")
        );
            Product product = Product.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .url(request.getUrl())
                    .category(category)
                    .build();

        return productReposiory.save(product);
    }
    //Lấy sản phẩm theo id
    public Product getProductById(long id){
        return productReposiory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
    }
    //Lấy tất cả danh sách sản phẩm
    public Page<ProductResponse> getAllProduct(PageRequest pageRequest){
        return productReposiory.findAll(pageRequest).map(product -> {
            ProductResponse productRespone = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .url(product.getUrl())
                    .description(product.getDescription())
                    .category_id(product.getCategory().getId())
                    .build();
            productRespone.setCreateAt(product.getCreateAt());
            productRespone.setUpdateAt(product.getUpdateAt());
            return productRespone;
        });
    }
    //Cập nhật sản phẩm
    public ProductResponse updateProduct(long id, ProductCreateRequest request) throws Exception{
        Product product = getProductById(id);
        Category category = categoryRepository
                .findById((long)(request.getCategory_id()))
                .orElseThrow(() ->
                        new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        product = productMapper.toProduct(request);
        return productMapper.toProductResponse(product);
    }

    //Xóa sản phẩm
    public void deleteProduct(long id){
        Optional<Product> product = productReposiory.findById(id);
        if(product.isPresent()){
            productReposiory.deleteById(id);
        }
    }

    public ProductImage createProductImage(long productId, ProductImageCreateRequest request) throws Exception {
        Product product = productReposiory
                .findById(productId)
                .orElseThrow(() ->
                        new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        ProductImage productImage = ProductImage.builder()
                .product(product)
                .imageUrl(request.getImageUrl())
                .build();
        //không cho truyền quá 5 ảnh vào một sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if(size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new Exception("Không thể thêm quá 5 ảnh");
        }
        return productImageRepository.save(productImage);
    }
}
