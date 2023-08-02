package toy.mytrip.app.member.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import toy.mytrip.app.member.exception.InvalidRequest;

import java.time.LocalDateTime;

@Getter
public class MemberEditor {
    private String LOGIN_ID = null;
    private String PASSWORD = null;
    private String PASSWORD_CHECK = null;
    private String NAME= null;
    private String RRN_ID= null;
    private String BIRTH= null;
    private String EMAIL= null;
    private String PHONE_NUMBER= null;
    private Long MILEAGE = null;
    private String AUTHORITY= null;
    private LocalDateTime UPDATE_TIME= null;
    private String UPDATE_ID= null;

    public MemberEditor(String LOGIN_ID, String PASSWORD, String PASSWORD_CHECK, String NAME, String RRN_ID, String BIRTH, String EMAIL, String PHONE_NUMBER, Long MILEAGE, String AUTHORITY, LocalDateTime UPDATE_TIME, String UPDATE_ID) {
        this.LOGIN_ID = LOGIN_ID;
        this.PASSWORD = PASSWORD;
        this.PASSWORD_CHECK = PASSWORD_CHECK;
        this.NAME = NAME;
        this.RRN_ID = RRN_ID;
        this.BIRTH = BIRTH;
        this.EMAIL = EMAIL;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.MILEAGE = MILEAGE;
        this.AUTHORITY = AUTHORITY;
        this.UPDATE_TIME = UPDATE_TIME;
        this.UPDATE_ID = UPDATE_ID;
    }

    public static MemberEditor.MemberEditorBuilder builder() { return new MemberEditor.MemberEditorBuilder();}

    public static class MemberEditorBuilder {
        private String LOGIN_ID;
        private String PASSWORD;
        private String PASSWORD_CHECK;
        private String NAME;
        private String RRN_ID;
        private String BIRTH;
        private String EMAIL;
        private String PHONE_NUMBER;
        private Long MILEAGE;
        private String AUTHORITY;
        private LocalDateTime UPDATE_TIME;
        private String UPDATE_ID;

        MemberEditorBuilder() {}
        public MemberEditor.MemberEditorBuilder LOGIN_ID(final String LOGIN_ID) {
            if( LOGIN_ID != null) this.LOGIN_ID = LOGIN_ID;
            return this;
        }
        public MemberEditor.MemberEditorBuilder PASSWORD(final String PASSWORD) {
            if( PASSWORD != null) this.PASSWORD = PASSWORD;
            return this;
        }
        public MemberEditor.MemberEditorBuilder PASSWORD_CHECK(final String PASSWORD_CHECK) {
            if( PASSWORD_CHECK != null) this.PASSWORD_CHECK = PASSWORD_CHECK;
            return this;
        }
        public MemberEditor.MemberEditorBuilder NAME(final String NAME) {
            if( NAME != null) this.NAME = NAME;
            return this;
        }
        public MemberEditor.MemberEditorBuilder RRN_ID(final String RRN_ID) {
            if( RRN_ID != null) this.RRN_ID = RRN_ID;
            return this;
        }
        public MemberEditor.MemberEditorBuilder BIRTH(final String BIRTH) {
            if( BIRTH != null) this.BIRTH = BIRTH;
            return this;
        }
        public MemberEditor.MemberEditorBuilder EMAIL(final String EMAIL) {
            if( EMAIL != null) this.EMAIL = EMAIL;
            return this;
        }
        public MemberEditor.MemberEditorBuilder PHONE_NUMBER(final String PHONE_NUMBER) {
            if( PHONE_NUMBER != null) this.PHONE_NUMBER = PHONE_NUMBER;
            return this;
        }
        public MemberEditor.MemberEditorBuilder MILEAGE(final Long MILEAGE) {
            if( MILEAGE != null) this.MILEAGE = MILEAGE;
            return this;
        }
        public MemberEditor.MemberEditorBuilder AUTHORITY(final String AUTHORITY) {
            if( AUTHORITY != null) this.AUTHORITY = AUTHORITY;
            return this;
        }
        public MemberEditor.MemberEditorBuilder UPDATE_TIME(final LocalDateTime UPDATE_TIME) {
            if( UPDATE_TIME != null) this.UPDATE_TIME = UPDATE_TIME;
            return this;
        }
        public MemberEditor.MemberEditorBuilder UPDATE_ID(final String UPDATE_ID) {
            if( UPDATE_ID != null) this.UPDATE_ID = UPDATE_ID;
            return this;
        }


        public MemberEditor build() {
            return new MemberEditor(
                    this.LOGIN_ID,
                    this.PASSWORD,
                    this.PASSWORD_CHECK,
                    this.NAME,
                    this.RRN_ID,
                    this.BIRTH,
                    this.EMAIL,
                    this.PHONE_NUMBER,
                    this.MILEAGE,
                    this.AUTHORITY,
                    this.UPDATE_TIME,
                    this.UPDATE_ID
            );
        }
    }
}
