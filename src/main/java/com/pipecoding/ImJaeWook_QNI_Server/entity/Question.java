package com.pipecoding.ImJaeWook_QNI_Server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Question {

    @Id
    private Long id;  // question_item 의 id 값을 가져와 set 한 이후 insert 할 것

    private String question;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "answer_id")
    private Answer answer;
}