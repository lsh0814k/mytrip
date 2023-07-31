package toy.mytrip.errors.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import toy.mytrip.errors.codes.ErrorCode;

@RequiredArgsConstructor
@Getter
public class MyTripException extends RuntimeException {
    private final ErrorCode errorCode;

    public String getStatus() {
        return errorCode.getStatus().toString();
    }

    public String getErrorMessage() {
        return errorCode.getErrorMessage();
    }
}
