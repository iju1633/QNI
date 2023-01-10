package com.pipecoding.ImJaeWook_QNI_Server.service;

import com.pipecoding.ImJaeWook_QNI_Server.dto.ChangeNicknameDTO;
import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import com.pipecoding.ImJaeWook_QNI_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SettingService {

    private final UserRepository userRepository;
    public void changeNickname(ChangeNicknameDTO changeNicknameDTO) {

        User user = userRepository.getUserById(changeNicknameDTO.getUserId());

        user.setNickname(changeNicknameDTO.getNickname());

        userRepository.save(user);
    }

    public void withdrawal(Long userId) {

        userRepository.delete(userRepository.getUserById(userId));
    }
}