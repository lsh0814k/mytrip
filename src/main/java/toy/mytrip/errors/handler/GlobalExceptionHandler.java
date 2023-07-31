package toy.mytrip.errors.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import toy.mytrip.errors.codes.DefaultErrorCodes;
import toy.mytrip.errors.codes.ErrorCode;
import toy.mytrip.errors.exception.MyTripException;
import toy.mytrip.errors.response.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ErrorResponse.FieldErrorDetail> fieldErrors = getFieldErrorDetails(e.getBindingResult());
        return buildFieldErrors(DefaultErrorCodes.INPUT_VALUE_INVALID, fieldErrors);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handlerBindException(BindException e) {
        final List<ErrorResponse.FieldErrorDetail> fieldErrors = getFieldErrorDetails(e.getBindingResult());
        return buildFieldErrors(DefaultErrorCodes.INPUT_VALUE_INVALID, fieldErrors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handlerException(Exception e) {
        return buildError(DefaultErrorCodes.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MyTripException.class)
    protected ResponseEntity<ErrorResponse> handlerMyTripException(MyTripException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(Integer.valueOf(errorCode.getStatus()))
                .body(buildError(e.getErrorCode()));
    }

    private ErrorResponse buildFieldErrors(ErrorCode errorCode, List<ErrorResponse.FieldErrorDetail> errors) {
        return ErrorResponse.builder()
                .status(errorCode.getStatus().toString())
                .message(errorCode.getErrorMessage())
                .fieldErrorDetails(errors)
                .build();
    }

    private List<ErrorResponse.FieldErrorDetail> getFieldErrorDetails(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> ErrorResponse.FieldErrorDetail.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .rejectValue(fieldError.getRejectedValue())
                        .build()
                )
                .collect(Collectors.toList());
    }

    private ErrorResponse buildError(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .status(errorCode.getStatus().toString())
                .message(errorCode.getErrorMessage())
                .build();
    }
}
