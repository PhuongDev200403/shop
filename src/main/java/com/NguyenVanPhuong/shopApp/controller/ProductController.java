package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.ProductImageCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.ProductListResponse;
import com.NguyenVanPhuong.shopApp.dto.Response.ProductResponse;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.NguyenVanPhuong.shopApp.entity.ProductImage;
import com.NguyenVanPhuong.shopApp.repository.ProductReposiory;
import com.NguyenVanPhuong.shopApp.service.ProductService;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductReposiory productRepository;

    @GetMapping()
    public ApiResponse<ProductListResponse> getAllProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("createAt").descending()
        );
        Page<ProductResponse> productPage = productService.getAllProduct(pageRequest);
        //lấy tổng số trang
        int totalPages = productPage.getTotalPages();
        List<ProductResponse> products = productPage.getContent();
        ProductListResponse productListResponse = ProductListResponse
                .builder()
                .products(products)
                .totalPage(totalPages)
                .build();
        return ApiResponse.<ProductListResponse>builder()
                //.success(true)
                .result(productListResponse)
                .build();
    }

    @PostMapping()
    public ApiResponse<?> createProduct(
            @RequestBody @Valid ProductCreateRequest request,
            //@ModelAttribute("files") List<MultipartFile> files,
            //@RequestPart("file") MultipartFile file,
            BindingResult result
    ){

        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();
                return ApiResponse.builder()
                        //.success(false)
                        .message("Gặp lỗi trong quá trình tạo sản phẩm")
                        .result(errorMessages)
                        .build();
            }
            Product product = productService.createProduct(request);

            return ApiResponse.builder()
                    //.success(true)
                    .result(productService.createProduct(request))
                    .build();
        }catch (Exception ex){
            return ApiResponse.builder()
                    //.success(false)
                    .result(ex.getMessage())
                    .build();
        }
    }
    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<?> uploadImages(
            @ModelAttribute("files") List<MultipartFile> files,
            @PathVariable("id") long productId
    ) throws Exception {
        Product product = productService.getProductById(productId);
        if(files.size() >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new Exception("Không thể thêm quá 5 ảnh");
        }
        //kiểm tra kích thước của ảnh
        files = files == null ? new ArrayList<MultipartFile>() : files;
        List<ProductImage> productImages = new ArrayList<>();
        for(var file : files){

            if(file != null){
                if(file.getSize() > 10 *1024 *1024){
                    throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File updated is too large");
                }
                //Kiểm tra xe file upload nên có phải ảnh hay không
                String contentType = file.getContentType();
                if(contentType == null || !contentType.startsWith("image/")){
                    throw  new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "File must be a image");
                }
                //lưu ảnh vào trong
                String fileName = storeFile(file);
                ProductImage productImage = productService.createProductImage(product.getId(), ProductImageCreateRequest.builder()
                        .imageUrl(fileName)
                        .build());
                productImages.add(productImage);
            }
        }
        return ApiResponse.builder()
                //.success(true)
                .result(productImages)
                .build();
    }
    private String storeFile(MultipartFile file)throws IOException {
        if(!isImageFile(file) || file.getOriginalFilename() == null){
            throw new IOException("File không hợp lệ");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));//lấy tên file
        String newFileName = UUID.randomUUID().toString() +"_"+ fileName;
        //Lấy ra thư mục chứa ảnh là uploads
        java.nio.file.Path uploadDir = Paths.get("uploads");
        if(!Files.exists(uploadDir)){
            //nếu trong uploads chưa có file uploadDir thì đẩy uploadDir vào
            Files.createDirectories(uploadDir);
        }
        //lấy ra đường dẫn đầy đủ của file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), newFileName);
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return newFileName;
    }
    private boolean isImageFile(MultipartFile file){
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
    @GetMapping("/{id}")
    public ApiResponse<?> getById(@PathVariable long id){
        try{
            Product product = productService.getProductById(id);
            ProductResponse products = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .url(product.getUrl())
                    .category_id(product.getCategory().getId())
                    .build();
            products.setUpdateAt(product.getUpdateAt());
            products.setCreateAt(product.getCreateAt());
            return ApiResponse.builder()
                    //.success(true)
                    .result(products)
                    .build();
        }catch (Exception e){
            return ApiResponse.builder()
                    //.success(false)
                    .result(e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

    @PostMapping("/generate")
    public ApiResponse<String> generateProducts(){
        Faker faker = new Faker();
        for(int i = 0 ; i < 10000; i++){
            String productName = faker.commerce().productName();
            if(productRepository.existsByName(productName)){
                continue;
            }
            ProductCreateRequest productCreateRequest = ProductCreateRequest
                    .builder()
                    .name(productName)
                    .price(faker.number().numberBetween(10, 900000000))
                    .description(faker.lorem().sentence())
                    .category_id(faker.number().numberBetween(55, 57))
                    .build();
            try{
                productService.createProduct(productCreateRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return ApiResponse.<String>builder()
                //.success(true)
                .result("Thành công")
                .build();
    }
}
