package openones.oopms.projecteye.validator;

import openones.oopms.projecteye.form.CreateProjectForm;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class CreateProjectValidator implements Validator{

	@Override
    public boolean supports(Class<?> arg0) {
       return CreateProjectForm.class.isAssignableFrom(arg0);
    }
    
	@Override
    public void validate(Object target, Errors errors) {
    	CreateProjectForm bean = (CreateProjectForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectCode", "error");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "error");
        
        if (bean.getProjectCode().length() != 3) {
            errors.rejectValue("projectCode", "CreateProject.projectCode.FieldLenght","ProjectCode much have 3 character");

        }
        
    }

}
