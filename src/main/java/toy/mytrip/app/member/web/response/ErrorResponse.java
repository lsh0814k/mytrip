package toy.mytrip.app.member.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonInclude( value =JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String,String> validation;
    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    private final Map<String,String> validationField = new HashMap<>();

    public void addValidation( String fieldName, String errorMsg) { this.validationField.put( fieldName, errorMsg);}
}
