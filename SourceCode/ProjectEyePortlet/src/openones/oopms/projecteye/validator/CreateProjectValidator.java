package openones.oopms.projecteye.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import openones.oopms.projecteye.dao.ProjectDao;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectCode", "CreateProject.projectCode.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "CreateProject.projectName.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planStartDate", "CreateProject.planStartDate.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planEndDate", "CreateProject.planEndDate.NotEmpty");
        
        if (bean.getProjectCode().length() != 3) {
            errors.rejectValue("projectCode", "CreateProject.projectCode.FieldLenght","ProjectCode much have 3 character");
        } else {
        	ProjectDao pDao = new ProjectDao();
        	if(pDao.checkDuplicateProjectCode(bean.getProjectCode())) {
        		errors.rejectValue("projectCode", "CreateProject.projectCode.Duplicate");
        	}
        }
        if (bean.getScopeObjective().length() > 600) {
            errors.rejectValue("scopeObjective", "CreateProject.scopeObjective.FieldMaxLenght");
        }
        if (bean.getProjectName().length() > 150) {
            errors.rejectValue("projectName", "CreateProject.projectName.FieldMaxLenght");
        }
        if (bean.getCustomer().length() > 150) {
            errors.rejectValue("customer", "CreateProject.customer.FieldMaxLenght");
        }
        if (bean.getEndCustomer().length() > 150) {
            errors.rejectValue("endCustomer", "CreateProject.endCustomer.FieldMaxLenght");
        }
        DateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date temp;
        try {
        	temp = (Date) formatter.parse(bean.getPlanStartDate());
        } catch (ParseException e) {
        	errors.rejectValue("planStartDate", "CreateProject.planStartDate.WrongDateFormat");
        }
        try {
        	temp = (Date) formatter.parse(bean.getPlanEndDate());
        } catch (ParseException e) {
        	errors.rejectValue("planEndDate", "CreateProject.planEndDate.WrongDateFormat");
        }

		
    }

}
