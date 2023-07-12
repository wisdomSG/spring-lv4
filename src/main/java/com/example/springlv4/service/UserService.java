package com.example.springlv4.service;

import com.example.springlv4.dto.SignupRequestDto;
import com.example.springlv4.dto.UserRequestDto;
import com.example.springlv4.entity.User;
import com.example.springlv4.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 메서드
    public void signup(SignupRequestDto dto) {
        String username = dto.getUsername();
        String password = passwordEncoder.encode(dto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);

    }

    // 로그인 메서드
    public void login(UserRequestDto dto, HttpServletResponse response) {
        String username = dto.getUsername();
        String password = passwordEncoder.encode(dto.getPassword());

        User user = findUser(username);
    }

    // 쿼리메서드 사용해서 username으로 DB에 user 찾기
    private User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 username이 존재하지 않습니다.")
        );
    }
}
