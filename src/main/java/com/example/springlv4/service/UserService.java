package com.example.springlv4.service;

import com.example.springlv4.dto.SignupRequestDto;
import com.example.springlv4.dto.UserRequestDto;
import com.example.springlv4.entity.User;
import com.example.springlv4.entity.UserRoleEnum;
import com.example.springlv4.repository.UserRepository;
import com.example.springlv4.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "44445"; // ADMIN_TOKEN: 일반사용자인지 관리자인지 구분하기위해서




    // 회원가입 메서드
    public void signup(SignupRequestDto dto) {
        String username = dto.getUsername();
        String password = passwordEncoder.encode(dto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 username 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        if(!dto.getAdminToken().isEmpty()) { //admin을 입력했는지 확인하고 입력했다면 true로 바꿔줌
            dto.setAdmin(true);
        }

        if (dto.isAdmin()) { // admin을 입력했다면 관리자 암호가 맞는지 확인
            if (!ADMIN_TOKEN.equals(dto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);

    }

    // 로그인 메서드
    public void login(UserRequestDto dto, HttpServletResponse response) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = findUser(username);

        // password 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }

        // JWT 생성 및 헤더에 추가
        String token = jwtUtil.createToken(username);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

    }

    // 쿼리메서드 사용해서 username으로 DB에 user 찾기
    private User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
    }
}
