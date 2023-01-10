package com.pipecoding.ImJaeWook_QNI_Server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeNicknameDTO {

    @NotBlank(message = "새로운 닉네임을 기입해주세요.")
    private String nickname;

    @NotBlank
    private Long userId;
}