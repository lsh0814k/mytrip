package toy.mytrip.member.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import toy.mytrip.member.domain.Autority;
import toy.mytrip.member.domain.Member;

@Getter
@RequiredArgsConstructor
public class MemberUpdateForm {
    @NotEmpty
    private final Long id;

    @NotEmpty
    @Length(max = 50)
    private final String loginId;

    @NotEmpty
    @Length(max = 25)
    private final String password;

    @NotEmpty
    @Length(max = 25)
    private final String passwordConf;

    @NotEmpty
    @Length(max = 150)
    private final String name;

    @NotEmpty
    @Length(max = 13)
    private final String rrnId;

    @NotEmpty
    @Length(max = 6)
    private final String birth;

    @NotEmpty
    @Length(max = 50)
    @Email
    private final String email;

    @NotEmpty
    @Length(max = 20)
    private final String phoneNumber;

    @NotEmpty
    private final Autority autority;

    public Member toMember() {
        return Member.builder()
                .id(this.id)
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .rrnId(this.rrnId)
                .birth(this.birth)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .autority(this.autority)
                .build();
    }
}
