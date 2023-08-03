package toy.mytrip.app.member.exception;

import lombok.Getter;

@Getter
public class InvalidRequest extends MemberException {
    private static final String MESSAGE = "잘못된 요청입니다.";
    public InvalidRequest(String message) {
        super(message);
    }

    public InvalidRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequest( String fieldName, String message){
        super( MESSAGE);
        addValidation( fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
