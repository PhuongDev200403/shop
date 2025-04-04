package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.ProductImageCreateRequest;
import com.NguyenVanPhuong.shopApp.entity.Category;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.NguyenVanPhuong.shopApp.entity.ProductImage;
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
                .orElseThrow(() -> new DataIntegrityViolationException("Không tìm thấy sản phẩm"));
    }
    //Lấy tất cả danh sách sản phẩm
    public Page<Product> getAllProduct(PageRequest pageRequest){
        return productReposiory.findAll(pageRequest);
    }
    //Cập nhật sản phẩm
    public Product updateProduct(long id, ProductCreateRequest request) throws Exception{
        Product product = getProductById(id);
        Category category = categoryRepository
                .findById((long)(request.getCategory_id()))
                .orElseThrow(() ->
                        new DataIntegrityViolationException(
                                "Không tìm thấy danh mục chứa sản phẩm"));

        product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .url(request.getUrl())
                .category(category)
                .build();
        return productReposiory.save(product);
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
                        new DataIntegrityViolationException(
                                "Không tìm thấy sản phẩm"));
        ProductImage productImage = ProductImage.builder()
                .product(product)
                .imageUrl(request.getImageUrl())
                .build();
        //không cho truyền quá 5 ảnh vào một sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if(size >= 5){
            throw new Exception("Không thể thêm quá 5 ảnh");
        }
        return productImageRepository.save(productImage);
    }
}
