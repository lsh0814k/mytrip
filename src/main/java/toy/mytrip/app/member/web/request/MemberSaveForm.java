package toy.mytrip.app.member.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import toy.mytrip.app.member.domain.Authority;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.domain.Password;

@Getter
@NoArgsConstructor
public class MemberSaveForm {

    @NotEmpty
    @Length(max = 50, min = 3)
    private String loginId;

    @NotEmpty
    @Length(max = 25, min = 8)
    private String password;

    @NotEmpty
    @Length(max = 25, min = 8)
    private String passwordConf;

    @NotEmpty
    @Length(max = 150)
    private String name;

    @NotEmpty
    @Length(max = 7, min = 7)
    private String rrnId;

    @NotEmpty
    @Length(max = 6, min = 6)
    private String birth;

    @NotEmpty
    @Length(max = 50)
    @Email
    private String email;

    @NotEmpty
    @Length(max = 20)
    private String phoneNumber;

    @NotEmpty
    private String authority;

    @Builder
    public MemberSaveForm(String loginId, String password, String passwordConf, String name, String rrnId, String birth, String email, String phoneNumber, String authority) {
        this.loginId = loginId;
        this.password = password;
        this.passwordConf = passwordConf;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public Member createMember() {
        Password pwd = new Password(this.password);
        pwd.checkConfPassword(this.passwordConf);

        return Member.builder()
                .loginId(this.loginId)
                .password(pwd)
                .name(this.name)
                .rrnId(this.rrnId)
                .birth(this.birth)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .authority(Authority.valueOf(this.authority))
                .build();
    }
}
