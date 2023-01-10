package com.pipecoding.ImJaeWook_QNI_Server.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@ToString(exclude = "questionAnswerList")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private String nickname;
    private String pwd;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Question_Answer> questionAnswerList = new ArrayList<>();
}