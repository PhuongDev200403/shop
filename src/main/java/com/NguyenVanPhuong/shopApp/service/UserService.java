package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.dto.Request.UserCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.UserLoginRequest;
import com.NguyenVanPhuong.shopApp.entity.Role;
import com.NguyenVanPhuong.shopApp.entity.User;
import com.NguyenVanPhuong.shopApp.repository.RoleRepository;
import com.NguyenVanPhuong.shopApp.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserReposiory userReposiory;
    @Autowired
    RoleRepository roleRepository;
    //Tạo tài khoản
    public User createUser(UserCreateRequest request){
        String phoneNumber = request.getPhoneNumber();
        if(userReposiory.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = User.builder()
                .fullName(request.getFullName())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .facebookAccountId((long)(request.getFaceBookAccountId()))
                .googleAccountId((long)(request.getGoogleAccountId()))
                .build();
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role không tồn tại!"));
        user.setRole(role);
        if(request.getGoogleAccountId() == 0 && request.getFaceBookAccountId() == 0){
            String password = request.getPassword();
            //String password = passwordEncoder.encode(request.getPassword());
            user.setPassword(password);
        }
        return userReposiory.save(user);
    }

    //Đăng nhập
    public String login(UserLoginRequest request){
        return  null;
    }
}
