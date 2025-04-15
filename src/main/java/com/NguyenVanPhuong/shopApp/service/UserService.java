package com.NguyenVanPhuong.shopApp.service;

import com.NguyenVanPhuong.shopApp.component.JwtTokenUtil;
import com.NguyenVanPhuong.shopApp.dto.Request.UserCreateRequest;
import com.NguyenVanPhuong.shopApp.dto.Request.UserLoginRequest;
import com.NguyenVanPhuong.shopApp.dto.Response.UserResponse;
import com.NguyenVanPhuong.shopApp.entity.Role;
import com.NguyenVanPhuong.shopApp.entity.User;
import com.NguyenVanPhuong.shopApp.exception.AppException;
import com.NguyenVanPhuong.shopApp.exception.ErrorCode;
import com.NguyenVanPhuong.shopApp.mapper.UserMapper;
import com.NguyenVanPhuong.shopApp.repository.RoleRepository;
import com.NguyenVanPhuong.shopApp.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserReposiory userReposiory;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;
    //Tạo tài khoản
    public UserResponse createUser(UserCreateRequest request){
        String phoneNumber = request.getPhoneNumber();
        if(userReposiory.existsByPhoneNumber(phoneNumber)){
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);
        }
        User user = userMapper.toUser(request);
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role không tồn tại!"));
        user.setRole(role);
        if(request.getGoogleAccountId() == 0 && request.getFaceBookAccountId() == 0){
            String password = request.getPassword();
            password = passwordEncoder.encode(request.getPassword());
            user.setPassword(password);
        }
        UserResponse userResponse = userMapper.toUserResponse(userReposiory.save(user));
        userResponse.setCreateAt(LocalDateTime.now());
        userResponse.setUpdateAt(LocalDateTime.now());
        return userResponse;
    }

    //Đăng nhập
    public String login(UserLoginRequest request){
        Optional<User> user = userReposiory.findByPhoneNumber(request.getPhoneNumber());
        if(user.isEmpty()){
            throw new AppException(ErrorCode.INVALID_ACCOUNT);
        }
        User userExisted = user.get();
        if ((userExisted.getGoogleAccountId() == null || userExisted.getGoogleAccountId() == 0) &&
                (userExisted.getFacebookAccountId() == null || userExisted.getFacebookAccountId() == 0)) {
            if (!passwordEncoder.matches(request.getPassword(), userExisted.getPassword())) {
                throw new BadCredentialsException("Password incorrect");
            }
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getPhoneNumber(),
                request.getPassword(), userExisted.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(userExisted);
    }
}
