package com.NguyenVanPhuong.shopApp.dto.Request;

import com.NguyenVanPhuong.shopApp.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageCreateRequest {
    @JsonProperty("product_id")
    @Min(value = 1, message = "id phải lớn hơn hoặc bàng 1")
    Long product_id;
    @Size(min = 5, max = 250, message = "file ảnh không hợp lệ")
    @JsonProperty("image_url")
    String imageUrl;
}
