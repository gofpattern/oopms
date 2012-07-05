package openones.oopms.validator;

import openones.oopms.form.LoginForm;
import oracle.jdbc.util.Login;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator{

    
    public boolean supports(Class<?> arg0) {
       return LoginForm.class.isAssignableFrom(arg0);
    }
    
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error");
        
    }

}
