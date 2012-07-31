package openones.oopms.projecteye.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.form.CreateProjectForm;

public class CreateProjectValidator {

	public String validate(Object target) {
		String error = "";
		CreateProjectForm bean = (CreateProjectForm) target;
		// validate Project Code
		if (bean.getProjectCode().equals("") || bean.getProjectCode() == null) {
			error = error + "Project Code is required" + "<br/>";
		} else if (bean.getProjectCode().length() != 3) {
			error = error + "ProjectCode much have 3 characters" + "<br/>";
		} else {
			ProjectDao pDao = new ProjectDao();
			if (pDao.checkDuplicateProjectCode(bean.getProjectCode())) {
				error = error + "Project Code has already in used" + "<br/>";
			}
		}
		// validate Project Name
		if (bean.getProjectName().equals("") || bean.getProjectName() == null) {
			error = error + "Project Name is required" + "<br/>";
		} else if (bean.getProjectName().length() > 150) {
			error = error + "Max length of Project Name: 150 characters"
					+ "<br/>";
		}
		// validate Date
		DateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date temp;
		if (bean.getPlanStartDate().equals("")
				|| bean.getPlanStartDate() == null) {
			error = error + "Planned Start Date is required is required"
					+ "<br/>";
		} else {
			try {
				temp = (Date) formatter.parse(bean.getPlanStartDate());
			} catch (ParseException e) {
				error = error + "Planned Start Date much has format mm/dd/yyyy"
						+ "<br/>";
			}
		}

		if (bean.getPlanEndDate().equals("") || bean.getPlanEndDate() == null) {
			error = error + "Planned End Date is required is required"
					+ "<br/>";
		} else {
			try {
				temp = (Date) formatter.parse(bean.getPlanEndDate());
			} catch (ParseException e) {
				error = error + "Planned End Date much has format mm/dd/yyyy"
						+ "<br/>";
			}
		}

		if (bean.getCustomer().length() > 150) {
			error = error + "Max length of Direct Customer: 150 characters"
					+ "<br/>";
		}

		if (bean.getEndCustomer().length() > 150) {
			error = error + "Max length of End Customer: 150 characters"
					+ "<br/>";
		}

		return error;

	}

}
