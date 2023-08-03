package toy.mytrip.app.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.mytrip.app.member.web.request.EditMember;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    private String loginId;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String rrnId;
    @NotNull
    private String birth;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    private Long mileage;
    private String authority;
    private LocalDateTime createTime;
    private String createId;
    private LocalDateTime updateTime;
    private String updateId;

    @Builder
    public Member(Long ID, String loginId, String password, String name, String rrnId, String birth, String email, String phoneNumber, Long mileage, String authority) {
        this.ID = ID;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.authority = authority;
        this.createTime = null;
        this.createId = null;
        this.updateTime = null;
        this.updateId = null;
    }

    public EditMember.MemberEditorBuilder toEdit() {
        return EditMember.builder()
                .loginId( loginId)
                .password( password)
                .name( name)
                .rrnId( rrnId)
                .birth( birth)
                .email( email)
                .passwordCheck( phoneNumber)
                .mileage( mileage)
                .authority( authority)
                .updateTime( updateTime)
                .updateId( updateId);
    }

    public void edit( EditMember memberEditor) {
        loginId = memberEditor.getLoginId();
        password = memberEditor.getPassword();
        name = memberEditor.getName();
        rrnId = memberEditor.getRrnId();
        birth = memberEditor.getBirth();
        email = memberEditor.getEmail();
        mileage = memberEditor.getMileage();
        authority = memberEditor.getAuthority();
        updateTime = memberEditor.getUpdateTime();
        phoneNumber = memberEditor.getPhoneNumber();
        updateId = memberEditor.getUpdateId();
    }

    @Override
    public String toString() {
        return "Member{" +
                "ID=" + ID +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", rrnId='" + rrnId + '\'' +
                ", birth='" + birth + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mileage=" + mileage +
                ", authority='" + authority + '\'' +
                ", createTime=" + createTime +
                ", createId='" + createId + '\'' +
                ", updateTime=" + updateTime +
                ", updateId='" + updateId + '\'' +
                '}';
    }
}
