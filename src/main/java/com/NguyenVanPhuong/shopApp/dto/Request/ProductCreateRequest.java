package com.NguyenVanPhuong.shopApp.dto.Request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
    @NotBlank(message = "This is required")
    @Size(min = 1,max = 200, message = "NAME_VALIDATION")
    String name;
    @Min(value = 0, message = "LOWER_PRICE")
    @Max(value = 10000000, message = "HIGHEST_PRICE" )
    float price;
    String url;
    String description;
    int category_id;
}
