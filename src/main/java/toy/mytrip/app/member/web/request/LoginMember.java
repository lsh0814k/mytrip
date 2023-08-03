package toy.mytrip.app.member.web.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toy.mytrip.app.member.exception.InvalidRequest;

@Getter
@Setter
public class LoginMember {
    private String loginId;
    private String password;

    @Builder
    public LoginMember(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public void isValid() {
        if( loginId.isEmpty() || loginId.isBlank()) {
            throw new InvalidRequest( "loginId", "로그인아이디를 입력하세요.");
        }
        if( password.isEmpty() || password.isBlank()) {
            throw new InvalidRequest( "password", "비밀번호를 입력하세요.");
        }
    }
}
