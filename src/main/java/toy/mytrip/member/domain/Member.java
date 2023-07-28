package toy.mytrip.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {
    public Member() {}

    @Builder
    public Member(Long id, String loginId, String password, String name, String rrnId, String birth, String email, String phoneNumber, Long mileage, Autority autority) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.autority = autority;
    }

    @Id @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String loginId;

    @Column(length = 25)
    private String password;

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
    private Autority autority;
}
