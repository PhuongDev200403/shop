package com.NguyenVanPhuong.shopApp.controller;

import com.NguyenVanPhuong.shopApp.dto.Request.ProductCreateRequest;
//import com.NguyenVanPhuong.shopApp.service.CategoryService;
import jakarta.validation.Valid;
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
public class ProductController {

//    @Autowired
//    CategoryService categoryService;

//    @PostMapping("/categories")
//    public ApiResponse<CategoryResponse> create(@RequestBody @Valid CategoryCreateRequest request){
//        return ApiResponse.<CategoryResponse>builder()
//                .result(categoryService.createCategory(request))
//                .build();
//    }
//    @GetMapping("/categories")
//    public ApiResponse<List<CategoryResponse>> getAll(){
//        return ApiResponse.<List<CategoryResponse>>builder()
//                .result(categoryService.getAll())
//                .build();
//    }
//    @PutMapping("/categories/{id}")
//    public ApiResponse<CategoryResponse> update(@PathVariable int id, @RequestBody CategoryUpdateRequest request){
//        return  ApiResponse.<CategoryResponse>builder()
//                .result(categoryService.updateCategory(id, request))
//                .build();
//    }
//    @DeleteMapping("/categories/{id}")
//    public ApiResponse<Void> delete(@PathVariable int id){
//        categoryService.deleteCategory(id);
//        return ApiResponse.<Void>builder().build();
//    }

    @GetMapping("/products")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok("getAllProducts");
    }

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCate(
            @ModelAttribute @Valid ProductCreateRequest request,
            //@RequestPart("file") MultipartFile file,
            BindingResult result
    ){

        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            List<MultipartFile> files = request.getFiles();
            //kiểm tra kích thước của ảnh
            files = files == null ? new ArrayList<MultipartFile>() : files;
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
                    String fileName = storeFile(file);
                }
            }
            return ResponseEntity.ok("created a product successfully ");
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
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
    @GetMapping("/products/{id}")
    public ResponseEntity<String> getById(@PathVariable int id){
        return ResponseEntity.ok("product id :" +id);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
