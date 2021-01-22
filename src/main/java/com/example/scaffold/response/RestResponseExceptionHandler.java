package com.example.scaffold.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseBody> handle(Exception e, HttpServletRequest request) {
        ExceptionResponseBody response = new ExceptionResponseBody();
        response.setTimestamp(LocalDateTime.now());
        response.setMessage("内部错误，请联系后端管理员查看");
        response.setPath(request.getRequestURI());
        return new ResponseEntity<ExceptionResponseBody>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}