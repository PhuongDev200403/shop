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
    UNAUTHORIZED(1005, "Unauthorized", HttpStatus.FORBIDDEN)
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}