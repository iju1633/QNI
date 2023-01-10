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
public class AnswerDTO {

    private Long userId;
    private Long questionId;

    @NotBlank(message = "답변을 입력해주세요.")
    private String answer;
}