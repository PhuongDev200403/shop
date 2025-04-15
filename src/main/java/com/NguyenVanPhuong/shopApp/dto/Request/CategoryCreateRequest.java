package com.NguyenVanPhuong.shopApp.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateRequest {
//    @Size(min = 1, message = "Name must be at least 1 character")
    @NotEmpty(message = "NAME_VALIDATION")
    String name;
}
