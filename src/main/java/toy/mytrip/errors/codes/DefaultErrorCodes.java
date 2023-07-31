package toy.mytrip.errors.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum DefaultErrorCodes implements ErrorCode {
    INPUT_VALUE_INVALID(HttpStatus.BAD_REQUEST.value(), "입력값이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버오류가 발생했습니다.")
    ;

    private final Integer status;
    private final String errorMessage;
}
