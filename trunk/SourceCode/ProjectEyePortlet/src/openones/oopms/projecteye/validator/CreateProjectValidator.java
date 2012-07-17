package openones.oopms.projecteye.validator;

import openones.oopms.projecteye.form.CreateProjectForm;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CreateProjectValidator implements Validator{

    
    public boolean supports(Class<?> arg0) {
       return CreateProjectForm.class.isAssignableFrom(arg0);
    }
    
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error");
        
    }

}
