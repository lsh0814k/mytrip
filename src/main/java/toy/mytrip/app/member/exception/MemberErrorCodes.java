package toy.mytrip.app.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import toy.mytrip.errors.codes.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCodes implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 사용자입니다."),
    DUPLICATE_LOGIN_ID(HttpStatus.BAD_REQUEST.value(), "동일한 ID가 존재합니다.")
    ;

    private final Integer status;
    private final String errorMessage;
}
