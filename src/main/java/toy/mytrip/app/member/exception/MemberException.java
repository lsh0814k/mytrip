package toy.mytrip.app.member.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MemberException extends RuntimeException {
    private final Map<String, String> validation = new HashMap<>();

    public MemberException( String message) { super(message);}

    public MemberException( String message, Throwable cause) { super( message, cause);}

    public abstract int getStatusCode();

    public void addValidation( String fieldName, String message) { validation.put( fieldName, message);}
}
