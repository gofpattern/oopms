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
		} else if (bean.getProjectCode().length() > 60) {
			error = error + "Max length of Project Code: 60 characters";
		} else {
			ProjectDao pDao = new ProjectDao();
			if (pDao.checkDuplicateProjectCode(bean.getProjectCode())) {
				error = error + "This project Code has already in used, please choose another one" + "<br/>";
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
		Date temp1 = new Date();
		Date temp2 = new Date();
		int flag = 0;
		if (bean.getPlanStartDate().equals("")
				|| bean.getPlanStartDate() == null) {
			error = error + "Planned Start Date is required is required"
					+ "<br/>";
			flag = 1;
		} else {
			try {
				temp1 = (Date) formatter.parse(bean.getPlanStartDate());
			} catch (ParseException e) {
				error = error + "Planned Start Date much has format mm/dd/yyyy"
						+ "<br/>";
				flag = 1;
			}
		}

		if (bean.getPlanEndDate().equals("") || bean.getPlanEndDate() == null) {
			error = error + "Planned End Date is required is required"
					+ "<br/>";
			flag = 1;
		} else {
			try {
				temp2 = (Date) formatter.parse(bean.getPlanEndDate());
			} catch (ParseException e) {
				error = error + "Planned End Date much has format mm/dd/yyyy"
						+ "<br/>";
				flag = 1;
			}
		}
		if (flag == 0) {
			if(temp1.after(temp2)) {
				error = error + "Planned End Date cannot before Planned Start Date"
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

		if (bean.getScopeObjective().length() > 600) {
			error = error + "Max length of Scope and Objective: 600 characters"
					+ "<br/>";
		}
		return error;

	}

}
