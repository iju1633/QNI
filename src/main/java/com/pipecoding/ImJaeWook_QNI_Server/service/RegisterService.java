package com.pipecoding.ImJaeWook_QNI_Server.service;

import com.pipecoding.ImJaeWook_QNI_Server.dto.RegisterDTO;
import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import com.pipecoding.ImJaeWook_QNI_Server.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository registerRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterDTO registerDTO) {

        // 회원가입 폼에서 아이디 혹은 닉네임이 기존 회원과 중복되는 값이 있는 지 확인
        // 동일하면 그대로 회원가입 진행, 아니라면 error 를 뱉어서 프론트에서 원하는 화면을 보여줄 수 있도록 할 것
        if (checkNickNameDuplicate(registerDTO.getNickname())) {
            throw new IllegalStateException("이미 사용 중인 닉네임이 있습니다.");
        } else if (checkUidDuplicate(registerDTO.getUid())) {
            throw new IllegalStateException("이미 사용 중인 아이디가 있습니다.");
        }

        // 회원가입 폼에서 입력받은 정보로 DTO 객체에 저장
        User user = new User();
        user.setUid(registerDTO.getUid());
        user.setNickname(registerDTO.getNickname());
        user.setPwd(passwordEncoder.encode(registerDTO.getPwd()));

        // DB에 사용자 저장
        registerRepository.save(user);
    }

    // 아이디 중복 체크
    public boolean checkUidDuplicate(String uid) {
        return registerRepository.existsByUid(uid);
    }

    // 닉네임 중복 체크
    public boolean checkNickNameDuplicate(String nickname) {
        return registerRepository.existsByNickname(nickname);
    }
}
