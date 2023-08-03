package toy.mytrip.app.member.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import toy.mytrip.app.member.exception.MemberException;
import toy.mytrip.app.member.web.response.ErrorResponse;

@ControllerAdvice
public class MemberExceptionController extends RuntimeException{
    @ResponseStatus( HttpStatus.BAD_REQUEST)
    @ExceptionHandler( MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code( "400")
                .message( "잘못된 요청입니다.")
                .build();
        e.getFieldError();
        for( FieldError filError : e.getFieldErrors()) {
            response.addValidation( filError.getField(), filError.getDefaultMessage());
        }

        return response;
    }

    @ResponseStatus( HttpStatus.NOT_FOUND)
    @ExceptionHandler({MemberException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponse> memberException( MemberException e) {
        int statusCode = e.getStatusCode();
        ErrorResponse body = ErrorResponse.builder()
                .code( String.valueOf( statusCode))
                .message( e.getMessage())
                .validation( e.getValidation())
                .build();
        ResponseEntity<ErrorResponse> response = ResponseEntity.status( statusCode)
                .body( body);

        return response;
    }
}
