package com.pipecoding.ImJaeWook_QNI_Server.controller;

import com.pipecoding.ImJaeWook_QNI_Server.dto.AnswerDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.AnswerUpdateDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.AnsweredQuestionDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.QuestionAnswerDTO;
import com.pipecoding.ImJaeWook_QNI_Server.service.QnAService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class QnAController {

    /*
     0. 인터셉터 구현해서 로그인 여부 체크하기
     1. 답변 작성하기
     2. 답변 수정하기
     3. 모든 질문 리스트 보여주기
     4. 답변한 질문 리스트 보여주기
     5. 워드 클라우드 생성을 위해 필요한 String 값 반환하기
     */

    /*
    구현 아이디어
    0. 1~4 작동 이전에 interceptor 를 통해 로그인이 되어있는 지 확인할 것.
    1. Answer 와 Question 객체 모두 생성 이후, Answer 객체를 Question 객체의 question 필드에 set 하고,
    question_item 의 유일한 값인 id 값을 활용하여 question 테이블에 insert 할때 setId 해서 넣는다.
        : body -> AnswerDTO(userId, questionId, answer)
    2. 수정할 questionId 와 수정 내용을 body 로 받아 수정한다.
        : body -> AnswerUpdateDTO(userId, questionId, newAnswer)
    3. question_item repository 를 활용하여 findAll 하면 된다.
    4. question 테이블을 활용하여 questionId, question, answer 를 포함한 객체가 리스트로 반환되게끔 할 것.
    5. answer 테이블의 모든 answer String 값을 append 하여 open api 요쳥을 한다.
        -> png 파일을 반환하는 형식이 뭔지 확실히 알고 post 요청 시, 세션을 활용하여 유저의 id 를 통해 question 테이블을 타고 answer 테이블로 넘어가 answer 들을 구할 수 있다.
     */

    private final QnAService qnAService;

    public QnAController(QnAService qnAService) {
        this.qnAService = qnAService;
    }

    @PostMapping("/answer/confirm")
    @ApiOperation(value = "답변 작성", notes = "답변을 작성하고 저장하는 로직이다.")
    public ResponseEntity<?> saveAnswer(@Validated @RequestBody AnswerDTO answerDTO) {

        qnAService.saveAnswer(answerDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/answer/update")
    @ApiOperation(value = "답변 수정", notes = "답변했던 내용을 수정하는 로직이다.")
    public ResponseEntity<?> updateAnswer(@Validated @RequestBody AnswerUpdateDTO answerUpdateDTO) {

        qnAService.updateAnswer(answerUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/question/list/userId/{userId}")
    @ApiOperation(value = "모든 질문 반환", notes = "답변 여부 관계없이 모든 질문을 반환한다.")
    public ResponseEntity<List<QuestionAnswerDTO>> getQuestionList(@PathVariable Long userId) {

        return ResponseEntity.ok().body(qnAService.getQuestionList(userId));
    }

    @GetMapping("/question/with-answer/list/userId/{userId}")
    @ApiOperation(value = "답변한 질문 반환", notes = "questionId, question, answer 를 포함한 객체가 리스트로 반환된다.")
    public ResponseEntity<List<AnsweredQuestionDTO>> getAnsweredQuestionList(@PathVariable Long userId) {

        return ResponseEntity.ok().body(qnAService.getAnsweredQuestionList(userId));
    }

    @GetMapping(value = "/word-cloud/userId/{userId}")
    @ApiOperation(value = "답변한 질문을 하나의 스트링으로 반환", notes = "여태 답변한 내용을 하나의 값으로 합쳐 반환한다.")
    public ResponseEntity<String> combineAnswersForWordCloud(@PathVariable Long userId) throws IOException {

        return ResponseEntity.ok().body(qnAService.combineAnswersForWordCloud(userId));
    }
}