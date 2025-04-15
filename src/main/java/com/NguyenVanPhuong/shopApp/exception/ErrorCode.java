package com.NguyenVanPhuong.shopApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    UNCATEGORY(9999, "Uncategory",HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_EXISTED(1001, "Category existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1002, "Category not existed", HttpStatus.NOT_FOUND),
    NAME_VALIDATION(1003, "Name must be at least {min} character", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1004, "Product not existed",HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1005, "Unauthorized", HttpStatus.FORBIDDEN),
    LOWER_PRICE(1006, "Price must be greater than {min} VNĐ", HttpStatus.BAD_REQUEST),
    HIGHEST_PRICE(1007, "Price must be less than {max} VNĐ", HttpStatus.BAD_REQUEST),
    INVALID_ID(1008, "Id Invalid", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_INVALID(1009, "Phone number invalid", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_EMPTY(999, "Address is required", HttpStatus.BAD_REQUEST),
    FULLNAME_NOT_EMPTY(998, "Fullname is required", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(997, "User not existed", HttpStatus.NOT_FOUND),
    PHONE_NUMBER_EXISTED(996, "Phone number existed", HttpStatus.BAD_REQUEST),
    ORDER_NOT_EXISTED(995, "Order not found", HttpStatus.NOT_FOUND),
    DATE_INVALID(994, "Date invalid", HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_NOT_EXISTED(993, "Order detail not found", HttpStatus.NOT_FOUND),
    INVALID_ACCOUNT(100, "Phone number or password incorrect", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(101, "Password must be at least {min} character", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(102, "Role not found", HttpStatus.NOT_FOUND)
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}