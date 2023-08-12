package toy.mytrip.app.member.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import toy.mytrip.app.member.exception.MemberErrorCodes;
import toy.mytrip.app.member.web.request.MemberSaveForm;

public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MemberSaveForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberSaveForm memberSaveForm = (MemberSaveForm) target;

        String password = memberSaveForm.getPassword();
        String passwordConf = memberSaveForm.getPasswordConf();

        if (!password.equals(passwordConf)) {
            errors.rejectValue("passwordConf", "", MemberErrorCodes.DIFF_CONF_PASSWORD.getErrorMessage());
        }
    }
}
