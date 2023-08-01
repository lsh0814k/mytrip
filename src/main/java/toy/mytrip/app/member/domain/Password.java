package toy.mytrip.app.member.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.mytrip.app.member.exception.MemberErrorCodes;
import toy.mytrip.errors.exception.MyTripException;

@Embeddable
@NoArgsConstructor
@Getter
public class Password {
    @Column(name = "password", length = 150)
    private String value;

    public Password(String value) {
        this.value = value;
    }

    private boolean isMatched(String password) {
        return value.equals(password);
    }

    public void checkConfPassword(String passwordConf) {
        if(! isMatched(passwordConf)) {
            throw new MyTripException(MemberErrorCodes.DIFF_CONF_PASSWORD);
        }
    }
}
