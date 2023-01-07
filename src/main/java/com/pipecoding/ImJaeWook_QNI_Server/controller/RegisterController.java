package com.pipecoding.ImJaeWook_QNI_Server.controller;

import com.pipecoding.ImJaeWook_QNI_Server.dto.RegisterDTO;
import com.pipecoding.ImJaeWook_QNI_Server.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    // 중복 에러가 없거나 비밀번호와 재확인용 비밀번호가 일치하면 등록 진행, 유효성 검사는 이미 적용되어 있음
    @PostMapping("/user/register")
    @ApiOperation(value = "회원 가입", notes = "회원가입을 진행하며 모든 필드 값은 null이 될 수 없습니다. \n")
    public ResponseEntity<?> registerUser(@Validated @RequestBody RegisterDTO registerDTO) {

        registerService.registerUser(registerDTO);
        return ResponseEntity.ok().build();
    }
}
