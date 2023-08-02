package toy.mytrip.app.member.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import toy.mytrip.app.member.exception.InvalidRequest;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateMember {

    @NotNull( message = "아이디를 입력하세요.")
    private String LOGIN_ID;
    @NotNull( message = "비밀번호를 입력하세요.")
    private String PASSWORD;
    @NotNull( message = "비밀번호를 입력하세요.")
    private String PASSWORD_CHECK;
    @NotNull( message = "이름을 입력하세요.")
    private String NAME;
    @NotNull( message = "주민번호를 입력하세요.")
    private String RRN_ID;
    @NotNull( message = "생년월일을 입력하세요.")
    private String BIRTH;
    @NotNull( message = "이메일을 입력하세요.")
    private String EMAIL;
    @NotNull( message = "핸드폰번호를 입력하세요.")
    private String PHONE_NUMBER;
    private Long MILEAGE;
    private String AUTHORITY;
    private LocalDateTime CREATE_TIME;
    private String CREATE_ID;
    private LocalDateTime UPDATE_TIME;
    private String UPDATE_ID;

    @Builder
    public CreateMember(String LOGIN_ID, String PASSWORD, String PASSWORD_CHECK, String NAME, String RRN_ID, String BIRTH, String EMAIL, String PHONE_NUMBER, Long MILEAGE, String AUTHORITY) {
        this.LOGIN_ID = LOGIN_ID;
        this.PASSWORD = PASSWORD;
        this.PASSWORD_CHECK = PASSWORD_CHECK;
        this.NAME = NAME;
        this.RRN_ID = RRN_ID;
        this.BIRTH = BIRTH;
        this.EMAIL = EMAIL;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.MILEAGE = MILEAGE;
        this.AUTHORITY = AUTHORITY;
        this.CREATE_TIME = LocalDateTime.now();
        this.CREATE_ID = null;
        this.UPDATE_TIME = LocalDateTime.now();
        this.UPDATE_ID = null;
    }

    public void isValid() {
        if( ! PASSWORD.equals( PASSWORD_CHECK)) {
            throw new InvalidRequest( "password", "비밀번호를 일치하지 않습니다.");
        }
    }
}
