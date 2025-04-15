package com.NguyenVanPhuong.shopApp.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @JsonProperty("fullname")
    String fullName;
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "PHONE_NUMBER_INVALID")
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "")
    String password;
    @JsonProperty("date_of_birth")
    LocalDate dateOfBirth;
    @JsonProperty("facebook_account_id")
    int faceBookAccountId;
    @JsonProperty("google_account_id")
    int googleAccountId;
    @JsonProperty("role_id")
    Long roleId;
}
