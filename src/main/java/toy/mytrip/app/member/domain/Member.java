package toy.mytrip.app.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Builder
    public Member(Long id, String loginId, Password password, String name, String rrnId, String birth, String email, String phoneNumber, Long mileage, Authority authority) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.authority = authority;
    }

    @Id @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String loginId;

    @Enumerated
    private Password password;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String rrnId;

    @Column(length = 8)
    private String birth;

    @Column(length = 50)
    private String email;

    @Column(length = 25)
    private String phoneNumber;

    private Long mileage;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public MemberEditor.MemberEditorBuilder toEditor() {
        return MemberEditor
                .builder()
                .name(this.name)
                .rrnId(this.rrnId)
                .birth(this.birth)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .authority(this.authority);
    }

    public void edit(MemberEditor editor) {
        this.name = editor.getName();
        this.rrnId = editor.getRrnId();
        this.birth = editor.getBirth();
        this.email = editor.getEmail();
        this.phoneNumber = editor.getPhoneNumber();
        this.authority = editor.getAuthority();
    }
}
