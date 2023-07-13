package com.example.springlv4.controller;

import com.example.springlv4.dto.UserRequestDto;
import com.example.springlv4.dto.SignupRequestDto;
import com.example.springlv4.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "UserController",description="사용자 API")
public class UserController {

    private final UserService userService;

    // 회원가입 기능 - signup
    @PostMapping("/user/signup")
    @Operation(summary = "회원가입 기능 API")
    public ResponseEntity<String> Signup(@RequestBody SignupRequestDto dto) {
        userService.signup(dto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    // 로그인 기능
    @PostMapping("/user/login")
    @Operation(summary = "로그인 기능 API")
    public ResponseEntity<String> Login(@RequestBody UserRequestDto dto, HttpServletResponse response) {
        userService.login(dto, response);
        return ResponseEntity.ok().body("로그인 성공 ");
    }
}
