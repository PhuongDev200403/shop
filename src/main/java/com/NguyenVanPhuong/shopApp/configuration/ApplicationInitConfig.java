package com.NguyenVanPhuong.shopApp.configuration;

import com.NguyenVanPhuong.shopApp.entity.User;
import com.NguyenVanPhuong.shopApp.exception.AppException;
import com.NguyenVanPhuong.shopApp.exception.ErrorCode;
import com.NguyenVanPhuong.shopApp.repository.RoleRepository;
import com.NguyenVanPhuong.shopApp.repository.UserReposiory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
@Slf4j
@Configuration
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserReposiory userRepository, RoleRepository roleRepository){
        return args -> {
            if(userRepository.findByPhoneNumber("admin").isEmpty()){
//                var roleId = new HashSet<Integer>();
//                //rolesId.add(role.ADMIN.name());
//                roleId.add(2);
                var roleAdmin = roleRepository.findById(2L).orElseThrow(
                        () -> new AppException(ErrorCode.ROLE_NOT_EXISTED)
                );
                var user = User.builder()
                        .phoneNumber("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(roleAdmin)
                        .build();

                userRepository.save(user);
                log.warn("Admin đã được tạo");
            }
        };
    }
}
