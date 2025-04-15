package com.NguyenVanPhuong.shopApp.mapper;

import com.NguyenVanPhuong.shopApp.dto.Request.UserCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.UserResponse;
import com.NguyenVanPhuong.shopApp.entity.Role;
import com.NguyenVanPhuong.shopApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true) // ánh xạ từ roleId → role
    User toUser(UserCreateRequest request);

    // Hàm hỗ trợ chuyển Long roleId → Role
//    default Role mapRole(Long roleId) {
//        if (roleId == null) return null;
//        return Role.builder().id(roleId).build(); // giả sử Role có @Builder
//    }
    @Mapping(source = "role.id", target = "roleId")
    UserResponse toUserResponse(User user);
}
