package com.pipecoding.ImJaeWook_QNI_Server.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class QnAController {

    /*
     1. 답변 작성하기
     2. 답변 수정하기
     3. 모든 질문 리스트 보여주기
     4. 답변한 질문 리스트 보여주기
     */

    /*
    구현 아이디어
    0. 1~4 작동 이전에 interceptor 를 통해 로그인이 되어있는 지 확인할 것
    1. Answer 와 Question 객체 모두 생성 이후, Answer 객체를 Question 객체의 question 필드에 set 하고,
    question_item 의 유일한 값인 id 값을 활용하여 question 테이블에 insert 할때 setId 해서 넣는다.
        : body -> AnswerDTO(id, questionId, answer)
    2. 수정할 questionId 와 수정 내용을 body 로 받아 수정한다.
        : body -> AnswerUpdateDTO(id, questionId, newAnswer)
    3. question_item repository 를 활용하여 findAll 하면 된다.
    4. question 테이블을 활용하여 questionId, question, answer 를 포함한 객체가 리스트로 반환되게끔 할 것
     */

}