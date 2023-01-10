package com.pipecoding.ImJaeWook_QNI_Server.service;

import com.pipecoding.ImJaeWook_QNI_Server.dto.AnswerDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.AnswerUpdateDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.AnsweredQuestionDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.QuestionAnswerDTO;
import com.pipecoding.ImJaeWook_QNI_Server.entity.Question_Answer;
import com.pipecoding.ImJaeWook_QNI_Server.repository.QuestionTableRepository;
import com.pipecoding.ImJaeWook_QNI_Server.repository.QuestionAnswerRepository;
import com.pipecoding.ImJaeWook_QNI_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnAService {

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
        : body -> AnswerDTO(questionId, answer)
    2. 수정할 questionId 와 수정 내용을 body 로 받아 수정한다.
        : body -> AnswerUpdateDTO(id, questionId, newAnswer)
    3. question_item repository 를 활용하여 findAll 하면 된다.
    4. question 테이블을 활용하여 questionId, question, answer 를 포함한 객체가 리스트로 반환되게끔 할 것.
    5. answer 테이블의 모든 answer String 값을 append 하여 open api 요쳥을 한다.
        -> png 파일을 반환하는 형식이 뭔지 확실히 알고 post 요청 시, 세션을 활용하여 유저의 id 를 통해 question 테이블을 타고 answer 테이블로 넘어가 answer 들을 구할 수 있다.
     */

    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserRepository userRepository;
    @Transactional
    public void saveAnswer(AnswerDTO answerDTO) {

        // 유저의 question에서 답변한 질문을 찾아 답을 기입
        Question_Answer question = questionAnswerRepository.getQuestionByIdAndUser(answerDTO.getQuestionId(), userRepository.getUserById(answerDTO.getUserId()));

        question.setAnswer(answerDTO.getAnswer());

        // save
        questionAnswerRepository.save(question);
    }

    @Transactional
    public void updateAnswer(AnswerUpdateDTO answerUpdateDTO) {

        // 유저의 question에서 답변한 질문을 찾아 새로운 답을 기입
        Question_Answer question = questionAnswerRepository.getQuestionByIdAndUser(answerUpdateDTO.getQuestionId(), userRepository.getUserById(answerUpdateDTO.getUserId()));

        if(question.getAnswer().equals("")) {
            throw new IllegalStateException("수정할 답변 내용이 없습니다.");
        }

        question.setAnswer(answerUpdateDTO.getNewAnswer());

        // save
        questionAnswerRepository.save(question);
    }

    public List<QuestionAnswerDTO> getQuestionList(Long userId) {

        List<Question_Answer> question_answerList = questionAnswerRepository.getAllByUser(userRepository.getUserById(userId));
        List<QuestionAnswerDTO> questionAnswerDTOList = new ArrayList<>();

        for (Question_Answer questionItem : question_answerList) {
            QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
            questionAnswerDTO.setQuestionId(questionItem.getId());
            questionAnswerDTO.setQuestion(questionItem.getQuestion());
            questionAnswerDTO.setAnswer(questionItem.getAnswer());

            questionAnswerDTOList.add(questionAnswerDTO);
        }

        return questionAnswerDTOList;
    }

    public List<AnsweredQuestionDTO> getAnsweredQuestionList(Long userId) {

        List<Question_Answer> question_answerList = questionAnswerRepository.getAllByUser(userRepository.getUserById(userId));
        List<AnsweredQuestionDTO> answeredQuestionList = new ArrayList<>();

        for (Question_Answer questionItem : question_answerList) {
            if(!questionItem.getAnswer().equals("")) { // 답변 내용이 있는 걸 찾는다
                AnsweredQuestionDTO answeredQuestionDTO = new AnsweredQuestionDTO();
                answeredQuestionDTO.setQuestionId(questionItem.getId());
                answeredQuestionDTO.setAnswer(questionItem.getAnswer());
                answeredQuestionDTO.setQuestion(questionItem.getQuestion());

                answeredQuestionList.add(answeredQuestionDTO);
            }
        }

        return  answeredQuestionList;
    }

    public String combineAnswersForWordCloud(Long userId) {
        List<Question_Answer> question_answerList = questionAnswerRepository.getAllByUser(userRepository.getUserById(userId));

        StringBuilder combinedAnswers = new StringBuilder();   // 출력할 문자열

        for (Question_Answer questionItem : question_answerList) {
            if(!questionItem.getAnswer().equals("")) { // 답변 내용이 있는 걸 찾아서 덧붙임
                combinedAnswers.append(questionItem.getAnswer());
            }
        }

        combinedAnswers.append(" "); // 다른 질문의 답변과 합쳐질 때 하나의 단어가 되는 것을 피하기 위함

        return combinedAnswers.toString();
    }
}
