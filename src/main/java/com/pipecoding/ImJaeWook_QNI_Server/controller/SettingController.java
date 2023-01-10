package com.pipecoding.ImJaeWook_QNI_Server.controller;

import com.pipecoding.ImJaeWook_QNI_Server.dto.ChangeNicknameDTO;
import com.pipecoding.ImJaeWook_QNI_Server.service.SettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PatchMapping("/setting/user/nickname")
    @ApiOperation(value = "닉네임 수정", notes = "새로운 닉네임과 유저의 id를 받아 작동합니다.")
    public ResponseEntity<?> changeNickname(@RequestBody ChangeNicknameDTO changeNicknameDTO) {

        settingService.changeNickname(changeNicknameDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/setting/withdrawal/userId/{userId}")
    @ApiOperation(value = "회원탈퇴", notes = "사용자를 삭제하며 사용자 관련 데이터가 사라집니다.")
    public ResponseEntity<?> withdrawal(@PathVariable Long userId) {

        settingService.withdrawal(userId);
        return ResponseEntity.ok().build();
    }
}