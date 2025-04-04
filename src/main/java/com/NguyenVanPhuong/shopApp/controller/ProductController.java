package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.ProductImageCreateRequest;
import com.NguyenVanPhuong.shopApp.entity.Product;
import com.NguyenVanPhuong.shopApp.entity.ProductImage;
import com.NguyenVanPhuong.shopApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

//    @GetMapping("/products")
//    public ApiResponse<Product> getAllCategories(
//            @RequestParam("page") int page,
//            @RequestParam("limit") int limit
//    ){
//        return ApiResponse.<Product>builder()
//                .success(true)
//                .result(productService.getAllProduct())
//                .build();
//    }

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
                        .success(false)
                        .message("Gặp lỗi trong quá trình tạo sản phẩm")
                        .result(errorMessages)
                        .build();
            }
            Product product = productService.createProduct(request);

            return ApiResponse.builder()
                    .success(true)
                    .result(productService.createProduct(request))
                    .build();
        }catch (Exception ex){
            return ApiResponse.builder()
                    .success(false)
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
                .success(true)
                .result(productImages)
                .build();
    }
    private String storeFile(MultipartFile file)throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());//lấy tên file
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
    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable int id){
        return ResponseEntity.ok("product id :" +id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
