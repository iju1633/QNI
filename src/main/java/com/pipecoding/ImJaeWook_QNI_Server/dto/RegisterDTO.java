package com.pipecoding.ImJaeWook_QNI_Server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "아이디를 기입해주세요.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pwd; // 비밀번호

    @NotBlank(message = "당신의 닉네임을 설정해주세요.")
    private String nickname; // 닉네임
}
