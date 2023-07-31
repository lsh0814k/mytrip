package toy.mytrip.app.member.web.response;

import lombok.Getter;
import toy.mytrip.app.member.domain.Member;

@Getter
public class MemberResponse {
    private String loginId;

    private String name;

    private String email;

    private String phoneNumber;

    private String authority;

    public MemberResponse() {}

    public MemberResponse(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.authority = member.getAuthority().toString();
    }
}
