package com.pipecoding.ImJaeWook_QNI_Server.controller;

import com.pipecoding.ImJaeWook_QNI_Server.dto.LoginDTO;
import com.pipecoding.ImJaeWook_QNI_Server.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호로 로그인하며, 로그인 성공 시, 유저의 nickname 을 반환합니다.")
    public ResponseEntity<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok().body(loginService.login(loginDTO.getUid(), loginDTO.getPwd()));
    }
}