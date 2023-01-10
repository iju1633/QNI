package com.pipecoding.ImJaeWook_QNI_Server.repository;

import com.pipecoding.ImJaeWook_QNI_Server.entity.Question_Answer;
import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<Question_Answer, Long> {

    Question_Answer getQuestionByIdAndUser(Long questionId, User user);

    List<Question_Answer> getAllByUser(User user);
}