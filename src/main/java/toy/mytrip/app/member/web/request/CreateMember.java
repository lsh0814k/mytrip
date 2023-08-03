package toy.mytrip.app.member.web.request;

import jakarta.validation.constraints.Email;
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
    private String loginId;

    @NotNull( message = "비밀번호를 입력하세요.")
    private String password;

    @NotNull( message = "비밀번호를 입력하세요.")
    private String passwordCheck;

    @NotNull( message = "이름을 입력하세요.")
    private String name;

    @NotNull( message = "주민번호를 입력하세요.")
    private String rrnId;

    @NotNull( message = "생년월일을 입력하세요.")
    private String birth;

    @NotNull( message = "이메일을 입력하세요.")
    @Email( message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotNull( message = "핸드폰번호를 입력하세요.")
    private String phoneNumber;

    private Long mileage;

    private String authority;

    private LocalDateTime createTime;

    private String createId;

    private LocalDateTime updateTime;

    private String updateId;

    @Builder
    public CreateMember(String loginId, String password, String passwordCheck, String name, String rrnId, String birth, String email, String phoneNumber, Long mileage, String authority) {
        this.loginId = loginId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.authority = authority;
        this.createTime = null;
        this.createId = null;
        this.updateTime = null;
        this.updateId = null;
    }

    public void isValid() {
        if( ! password.equals( passwordCheck)) {
            throw new InvalidRequest( "password", "비밀번호를 일치하지 않습니다.");
        }
    }
}
