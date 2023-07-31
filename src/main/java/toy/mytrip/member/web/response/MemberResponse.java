package toy.mytrip.member.web.response;

import lombok.Builder;
import lombok.NoArgsConstructor;
import toy.mytrip.member.domain.Authority;
import toy.mytrip.member.domain.Member;


@NoArgsConstructor
public class MemberResponse {
    private String loginId;

    private String name;

    private String rrnId;

    private String birth;

    private String email;

    private String phoneNumber;

    private Authority authority;

    @Builder
    public MemberResponse(String loginId, String name, String rrnId, String birth, String email, String phoneNumber, Authority authority) {
        this.loginId = loginId;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public MemberResponse create(Member member) {
        return MemberResponse.builder()
                .loginId(member.getLoginId())
                .name(member.getName())
                .rrnId(member.getRrnId())
                .birth(member.getBirth())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .authority(member.getAuthority())
                .build();
    }
}
