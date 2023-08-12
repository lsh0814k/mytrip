package toy.mytrip.app.member.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import toy.mytrip.app.member.domain.Authority;
import toy.mytrip.app.member.domain.Member;

@Getter
@NoArgsConstructor
public class MemberUpdateForm {
    @NotEmpty
    private Long id;

    @NotEmpty
    @Length(max = 50)
    private String loginId;

    @NotEmpty
    @Length(max = 150)
    private String name;

    @NotEmpty
    @Length(max = 13)
    private String rrnId;

    @NotEmpty
    @Length(max = 6)
    private String birth;

    @NotEmpty
    @Length(max = 50)
    @Email
    private String email;

    @NotEmpty
    @Length(max = 20)
    private String phoneNumber;

    @NotEmpty
    private Authority authority;

    @Builder
    public MemberUpdateForm(Long id, String loginId, String name, String rrnId, String birth, String email, String phoneNumber, Authority authority) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public Member toMember() {
        return Member.builder()
                .id(this.id)
                .loginId(this.loginId)
                .name(this.name)
                .rrnId(this.rrnId)
                .birth(this.birth)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .authority(this.authority)
                .build();
    }
}
