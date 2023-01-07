package com.pipecoding.ImJaeWook_QNI_Server.repository;

import com.pipecoding.ImJaeWook_QNI_Server.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
