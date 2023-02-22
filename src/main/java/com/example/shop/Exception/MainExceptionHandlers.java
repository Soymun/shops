package com.example.shop.Exception;


import com.example.shop.Response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

@ControllerAdvice
@Slf4j
public class MainExceptionHandlers {

    @ExceptionHandler({NoFoundException.class, FoundException.class})
    public ResponseEntity<?> exc(RuntimeException e){
        log.info("Предвиденные ошибки");
        return ResponseEntity.ok(ResponseDto.builder().error(e.getMessage()).build());
    }

    @ExceptionHandler({NoResultException.class, NonUniqueResultException.class})
    public ResponseEntity<?> sql(RuntimeException e){
        log.error(e.getMessage());
        return ResponseEntity.ok(ResponseDto.builder().error("Ошибка в данных. Попробуйте позже").build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> run(RuntimeException e){
        log.error(e.getMessage());
        return ResponseEntity.ok(ResponseDto.builder().error("Что-то произошло.\nПросим прощения.\nПопробуйте позже").build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> main(Exception e){
        log.error(e.getMessage());
        return ResponseEntity.ok(ResponseDto.builder().error("Похоже что-то серьёзное, не бойтесь, вы не виноваты, виноват Руслан.").build());
    }
}
