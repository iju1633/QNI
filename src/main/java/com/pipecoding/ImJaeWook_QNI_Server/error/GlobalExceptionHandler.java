package com.pipecoding.ImJaeWook_QNI_Server.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /*
    현재 서버에서 던지는 예외 정리
    1. 규칙 : 내림차순으로 정리하며, 코드도 순서에 맞게 작성할 것!
    2. 마지막 에러는 Exception 으로, 다루지 못한 에러에 대해서도 일관성 있는 data 를 반환하기 위해 작성

    <에러코드 400>
    MethodArgumentNotValidException : DTO 에서 validation 오류

    <에러코드 403> // client 에서 처리할 수도 있음
    AccessDeniedException : 로그인 되지 않은 채 서비스 요청

    <에러코드 405>
    MethodNotAllowedException : 잘못된 url 로 api 요청하는 경우

    <에러코드 500>
    IllegalStateException : 폼에서 기존의 회원 정보와 중복 발생 / 수정할 답변이 없는데 수정하고자 할때
    IllegalArgumentException : 로그인 시 아이디 존재하지 않거나 비밀번호 틀렸을 때
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        log.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse("400", e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedExceptionHandler(AccessDeniedException e) {

        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("403", e.getMessage()));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorResponse> methodNotAllowedExceptionHandler(MethodNotAllowedException e) {

        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorResponse("405", e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalStateExceptionHandler(IllegalStateException e) {

        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse("500", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {

        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse("500", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {

        log.error(e.getMessage());
        return new ErrorResponse("Unspecified", e.getMessage());
    }
}