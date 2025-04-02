//package com.NguyenVanPhuong.shopApp.exception;
//
//import com.NguyenVanPhuong.shopApp.dto.Request.ApiResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.Map;
//import java.util.Objects;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = AppException.class)
//    ResponseEntity<ApiResponse> handlingAppException(AppException ex){
//        ApiResponse apiResponse = new ApiResponse();
//        ErrorCode errorCode = ex.getErrorCode();
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(errorCode.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException ex){
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(ErrorCode.UNCATEGORY.getCode());
//        apiResponse.setMessage(ErrorCode.UNCATEGORY.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//}
