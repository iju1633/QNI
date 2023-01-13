package com.pipecoding.ImJaeWook_QNI_Server.service;

import com.pipecoding.ImJaeWook_QNI_Server.dto.LoginDTO;
import com.pipecoding.ImJaeWook_QNI_Server.dto.LoginResponseDTO;
import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import com.pipecoding.ImJaeWook_QNI_Server.repository.LoginRepository;
import com.pipecoding.ImJaeWook_QNI_Server.util.Const;
import com.pipecoding.ImJaeWook_QNI_Server.util.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginDTO loginDTO) {

        User loginMember = loginRepository.getUserByUid(loginDTO.getUid());
        log.info(String.valueOf(loginMember));

        // 아이디 존재하지 않는 경우
        if (loginMember == null) { // 받은 uid 로 회원이 존재하는 지 확인
            throw new IllegalArgumentException("아이디가 존재하지 않습니다.");
        }

        // 관리자/일반 테스트/일반 유저 로그인
        if (loginMember.getUid().equals(Const.TEST_USER_UID)) { // 테스트 계정의 경우
            if (!loginDTO.getPwd().equals(Const.TEST_PWD)) {
                throw new IllegalArgumentException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
            }
        } else { // 테스트 계정이 아닌 경우
            if (!passwordEncoder.matches(loginDTO.getPwd(), loginMember.getPwd())) {
                throw new IllegalArgumentException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
            }
        }

        // 신규 세션 생성
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        // 세션에 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        log.info("기존의 세션 반환 및 혹은 세션을 생성하였습니다.");
        log.info("해당 세션 : " + session);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setNickname(loginMember.getNickname());

        return loginResponseDTO;
    }
}