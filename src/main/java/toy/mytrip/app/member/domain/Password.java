package toy.mytrip.app.member.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Password {
    @Column(name = "password", length = 150)
    private String value;

    public Password(String value) {
        this.value = value;
    }
}
