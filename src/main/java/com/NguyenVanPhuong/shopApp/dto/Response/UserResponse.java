package com.NguyenVanPhuong.shopApp.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse extends BaseResponse{
    Long id;
    String fullName;
    String phoneNumber;
    String address;
//    String password;
    LocalDate dateOfBirth;
    int faceBookAccountId;
    int googleAccountId;
    Long roleId;
}
