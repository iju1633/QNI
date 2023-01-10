package com.pipecoding.ImJaeWook_QNI_Server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@Table(name = "question_answer")
@ToString(exclude = "user")
public class Question_Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String answer;
}