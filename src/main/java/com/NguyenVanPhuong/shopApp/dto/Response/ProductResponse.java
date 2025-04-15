package com.NguyenVanPhuong.shopApp.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse extends BaseResponse{
    Long id;
    String name;
    float price;
    String url;
    String description;
    int category_id;
}
