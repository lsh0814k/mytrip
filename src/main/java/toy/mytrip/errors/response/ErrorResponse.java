package toy.mytrip.errors.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private final String message;
    private final String status;
    private final List<FieldErrorDetail> fieldErrorDetails;

    @Builder
    public ErrorResponse(String message, String status, List<FieldErrorDetail> fieldErrorDetails) {
        this.message = message;
        this.status = status;
        this.fieldErrorDetails = initFieldDetails(fieldErrorDetails);
    }

    private List<FieldErrorDetail> initFieldDetails(List<FieldErrorDetail> fieldErrorDetails) {
        if (fieldErrorDetails == null) {
            return new ArrayList<>();
        }

        return fieldErrorDetails;
    }

    @Getter
    public static class FieldErrorDetail {
        private final String field;
        private final String message;
        private final Object rejectValue;

        @Builder
        public FieldErrorDetail(String field, String message, Object rejectValue) {
            this.field = field;
            this.message = message;
            this.rejectValue = rejectValue;
        }
    }

}
