package com.pipecoding.ImJaeWook_QNI_Server.service;

import com.pipecoding.ImJaeWook_QNI_Server.dto.ChangeNicknameDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.ChangeNicknameResponseDTO;
import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import com.pipecoding.ImJaeWook_QNI_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SettingService {

    private final UserRepository userRepository;
    public ChangeNicknameResponseDTO changeNickname(ChangeNicknameDTO changeNicknameDTO) {

        User user = userRepository.getUserById(changeNicknameDTO.getUserId());

        List<User> userList = userRepository.findAll();
        for (User item : userList) {
            if (changeNicknameDTO.getNickname().equals(item.getNickname())) {
                throw new IllegalStateException("이미 사용 중인 닉네임이 있습니다.");
            }
        }

        user.setNickname(changeNicknameDTO.getNickname());
        userRepository.save(user);

        ChangeNicknameResponseDTO changeNicknameResponseDTO = new ChangeNicknameResponseDTO();
        changeNicknameResponseDTO.setNewNickname(user.getNickname());

        return changeNicknameResponseDTO;
    }

    public void withdrawal(Long userId) {
        userRepository.delete(userRepository.getUserById(userId));
    }
}