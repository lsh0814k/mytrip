package toy.mytrip.app.member.web.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EditMember {
    private String loginId = null;
    private String password = null;
    private String passwordCheck = null;
    private String name= null;
    private String rrnId= null;
    private String birth= null;
    private String email= null;
    private String phoneNumber= null;
    private Long mileage = null;
    private String authority= null;
    private LocalDateTime updateTime= null;
    private String updateId= null;

    public EditMember(String loginId, String password, String passwordCheck, String name, String rrnId, String birth, String email, String phoneNumber, Long mileage, String authority, LocalDateTime updateTime, String updateId) {
        this.loginId = loginId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.rrnId = rrnId;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
        this.authority = authority;
        this.updateTime = updateTime;
        this.updateId = updateId;
    }

    public static EditMember.MemberEditorBuilder builder() { return new EditMember.MemberEditorBuilder();}

    public static class MemberEditorBuilder {
        private String loginId;
        private String password;
        private String passwordCheck;
        private String name;
        private String rrnId;
        private String birth;
        private String email;
        private String phoneNumber;
        private Long mileage;
        private String authority;
        private LocalDateTime updateTime;
        private String updateId;

        MemberEditorBuilder() {}
        public EditMember.MemberEditorBuilder loginId(final String loginId) {
            if( loginId != null) this.loginId = loginId;
            return this;
        }
        public EditMember.MemberEditorBuilder password(final String password) {
            if( password != null) this.password = password;
            return this;
        }
        public EditMember.MemberEditorBuilder passwordCheck(final String passwordCheck) {
            if( passwordCheck != null) this.passwordCheck = passwordCheck;
            return this;
        }
        public EditMember.MemberEditorBuilder name(final String name) {
            if( name != null) this.name = name;
            return this;
        }
        public EditMember.MemberEditorBuilder rrnId(final String rrnId) {
            if( rrnId != null) this.rrnId = rrnId;
            return this;
        }
        public EditMember.MemberEditorBuilder birth(final String birth) {
            if( birth != null) this.birth = birth;
            return this;
        }
        public EditMember.MemberEditorBuilder email(final String email) {
            if( email != null) this.email = email;
            return this;
        }
        public EditMember.MemberEditorBuilder phoneNumber(final String phoneNumber) {
            if( phoneNumber != null) this.phoneNumber = phoneNumber;
            return this;
        }
        public EditMember.MemberEditorBuilder mileage(final Long mileage) {
            if( mileage != null) this.mileage = mileage;
            return this;
        }
        public EditMember.MemberEditorBuilder authority(final String authority) {
            if( authority != null) this.authority = authority;
            return this;
        }
        public EditMember.MemberEditorBuilder updateTime(final LocalDateTime updateTime) {
            if( updateTime != null) this.updateTime = updateTime;
            return this;
        }
        public EditMember.MemberEditorBuilder updateId(final String updateId) {
            if( updateId != null) this.updateId = updateId;
            return this;
        }


        public EditMember build() {
            return new EditMember(
                    this.loginId,
                    this.password,
                    this.passwordCheck,
                    this.name,
                    this.rrnId,
                    this.birth,
                    this.email,
                    this.phoneNumber,
                    this.mileage,
                    this.authority,
                    this.updateTime,
                    this.updateId
            );
        }
    }
}
