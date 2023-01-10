package com.pipecoding.ImJaeWook_QNI_Server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnsweredQuestionDTO {

    private Long questionId;
    private String question;
    private String answer;
}