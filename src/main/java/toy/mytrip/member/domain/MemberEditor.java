package toy.mytrip.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEditor {
    private String name;

    private String rrnId;

    private String birth;

    private String email;

    private String phoneNumber;

    private Authority authority;

    @Builder
    public MemberEditor(String name, String rrnId, String birth, String email, String phoneNumber, Authority authority) {
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }
}
