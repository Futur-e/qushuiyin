package com.example.huafeng_serve.common.utils.exception;

import com.example.huafeng_serve.common.utils.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// 全局异常处理
@ControllerAdvice
public class GlobalExceptionHandler {
    // 处理自定义异常
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<R<Object>> handleCustomException(CustomException ex) {
        R<Object> response = R.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    // 处理其他未捕获的异常
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<R<Object>> handleException(Exception ex) {
//        System.out.println(ex);
//        R<Object> response = R.error("服务异常,请及时联系客服或添加微信q2637844016");
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
