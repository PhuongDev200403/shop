package com.NguyenVanPhuong.shopApp.dto.Request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
    @NotBlank(message = "This is required")
    @Size(min = 1,max = 200, message = "Name must be at least 1 character")
    String name;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000000, message = "Price must be less than or equal to 10000000" )
    float price;
    String url;
    String description;
    int category_id;
    List<MultipartFile> files;

}
