package openones.oopms.validator;

import openones.oopms.form.RequirementForm;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator{

    
    public boolean supports(Class<?> arg0) {
       return RequirementForm.class.isAssignableFrom(arg0);
    }
    
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error");
        
    }

}
