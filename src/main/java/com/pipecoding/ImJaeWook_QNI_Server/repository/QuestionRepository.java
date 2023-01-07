package com.pipecoding.ImJaeWook_QNI_Server.repository;

import com.pipecoding.ImJaeWook_QNI_Server.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByAnswered(boolean answered);
}
