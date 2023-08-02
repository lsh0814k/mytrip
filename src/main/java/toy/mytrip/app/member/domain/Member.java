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

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    private String LOGIN_ID;
    @NotNull
    private String PASSWORD;
    @NotNull
    private String NAME;
    @NotNull
    private String RRN_ID;
    @NotNull
    private String BIRTH;
    @NotNull
    private String EMAIL;
    @NotNull
    private String PHONE_NUMBER;
    private Long MILEAGE;
    private String AUTHORITY;
    private LocalDateTime CREATE_TIME;
    private String CREATE_ID;
    private LocalDateTime UPDATE_TIME;
    private String UPDATE_ID;

    @Builder
    public Member(Long ID, String LOGIN_ID, String PASSWORD, String NAME, String RRN_ID, String BIRTH, String EMAIL, String PHONE_NUMBER, Long MILEAGE, String AUTHORITY) {
        this.ID = ID;
        this.LOGIN_ID = LOGIN_ID;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
        this.RRN_ID = RRN_ID;
        this.BIRTH = BIRTH;
        this.EMAIL = EMAIL;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.MILEAGE = MILEAGE;
        this.AUTHORITY = AUTHORITY;
        this.CREATE_TIME = LocalDateTime.now();
        this.CREATE_ID = null;
        this.UPDATE_TIME = LocalDateTime.now();
        this.UPDATE_ID = null;
    }

    public MemberEditor.MemberEditorBuilder toEdit() {
        return MemberEditor.builder()
                .LOGIN_ID( LOGIN_ID)
                .PASSWORD( PASSWORD)
                .NAME( NAME)
                .RRN_ID( RRN_ID)
                .BIRTH( BIRTH)
                .EMAIL( EMAIL)
                .PHONE_NUMBER( PHONE_NUMBER)
                .MILEAGE( MILEAGE)
                .AUTHORITY( AUTHORITY)
                .UPDATE_TIME( UPDATE_TIME)
                .UPDATE_ID( UPDATE_ID);
    }

    public void edit( MemberEditor memberEditor) {
        LOGIN_ID = memberEditor.getLOGIN_ID();
        PASSWORD = memberEditor.getPASSWORD();
        NAME = memberEditor.getNAME();
        RRN_ID = memberEditor.getRRN_ID();
        BIRTH = memberEditor.getBIRTH();
        EMAIL = memberEditor.getEMAIL();
        MILEAGE = memberEditor.getMILEAGE();
        AUTHORITY = memberEditor.getAUTHORITY();
        UPDATE_TIME = memberEditor.getUPDATE_TIME();
        UPDATE_ID = memberEditor.getUPDATE_ID();
    }

    @Override
    public String toString() {
        return "Member{" +
                "ID=" + ID +
                ", LOGIN_ID='" + LOGIN_ID + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", NAME='" + NAME + '\'' +
                ", RRN_ID='" + RRN_ID + '\'' +
                ", BIRTH='" + BIRTH + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PHONE_NUMBER='" + PHONE_NUMBER + '\'' +
                ", MILEAGE=" + MILEAGE +
                ", AUTHORITY='" + AUTHORITY + '\'' +
                ", CREATE_TIME=" + CREATE_TIME +
                ", CREATE_ID='" + CREATE_ID + '\'' +
                ", UPDATE_TIME=" + UPDATE_TIME +
                ", UPDATE_ID='" + UPDATE_ID + '\'' +
                '}';
    }
}
