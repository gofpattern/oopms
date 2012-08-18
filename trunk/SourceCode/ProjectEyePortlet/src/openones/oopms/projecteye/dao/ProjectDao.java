package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.LanguageCode;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.OopmsProjectCost;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Stage;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.CostUtil;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProjectDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public List<Project> getProjectList(BigDecimal developerId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Project where projectId IN (Select project from Assignment WHERE developerId = :devId and ((endDate > :currentDate) or (endDate is null)) )";
			Query query = session.createQuery(hql);
			query.setParameter("devId", developerId);
			query.setParameter("currentDate", new Date());
			List<Project> projectList = query.list();
			session.flush();
			session.getTransaction().commit();
			System.out.println("Project Name Count : " + projectList.size());
			log.error("Project Name Count : " + projectList.size());
			return projectList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Use to create a new project
	 * 
	 * @param project
	 * @return
	 */
	public boolean insertProject(Project project, Assignment assignment) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(project);
			session.flush();
			// insert assigment for project owner
			assignment.setProject(project);
			session.save(assignment);
			// insert Project Stage
			WorkOrderDao woDao = new WorkOrderDao();
			List<Stage> stageList = woDao.getStandardStageList();
			for (int i = 0; i < stageList.size(); i++) {
				Milestone stage = new Milestone();
				stage.setProject(project);
				stage.setComplete(new BigDecimal(Constant.MilestoneUncomplete));
				stage.setName(stageList.get(i).getName());
				if (i == (stageList.size() - 1)) {
					stage.setPlanFinishDate(project.getPlanFinishDate());
				}
				session.save(stage);
				session.flush();
			}
			//insert Project Cost
			OopmsProjectCost projectCost = new OopmsProjectCost();
			projectCost.setProjectId(project.getProjectId());
			projectCost.setCostStatus("1");
			projectCost.setCurrentBudget(new BigDecimal("0"));
			projectCost.setCurrentExpense(new BigDecimal("0"));
			session.save(projectCost);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert succeed");
		return true;
	}

	public boolean updateProject(Project project) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(project);
			session.flush();
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Update fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert succeed");
		return true;
	}

	public boolean deleteProject(String projectId) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			String hql = "delete From Project where projectId = :projectId)";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", new BigDecimal(projectId));
			query.executeUpdate();
			session.flush();
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Delete fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Delete succeed");
		return true;
	}

	public List<GeneralReference> getProjectStatusList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From GeneralReference where groupCode = :gCode and languageCode = :lCode";
			Query query = session.createQuery(hql);
			query.setParameter("gCode", "PROJECT_STATUS");
			LanguageCode lCode = new LanguageCode();
			lCode.setLangCode("en");
			query.setParameter("lCode", lCode);
			List<GeneralReference> projectStatusList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Project Status Count : " + projectStatusList.size());
			return projectStatusList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<GeneralReference> getProjectCategoryList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From GeneralReference where groupCode = :gCode and languageCode = :lCode";
			Query query = session.createQuery(hql);
			query.setParameter("gCode", "PROJECT_CATEGORY");
			LanguageCode lCode = new LanguageCode();
			lCode.setLangCode("en");
			query.setParameter("lCode", lCode);
			List<GeneralReference> projectCategoryList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Project Category Count : " + projectCategoryList.size());
			return projectCategoryList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<BusinessDomain> getProjectBussinessDomainList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From BusinessDomain";
			Query query = session.createQuery(hql);
			List<BusinessDomain> projectBussinessDomainList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Project Bussiness Domain Count : "
					+ projectBussinessDomainList.size());
			return projectBussinessDomainList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public Project getProject(String projectId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Project where projectId = :projectID";
			Query query = session.createQuery(hql);
			query.setParameter("projectID", new BigDecimal(projectId));
			Project project = (Project) query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return project;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public GeneralReference getProjectStatus(String id) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From GeneralReference where generalRefId = :generalRefId";
			Query query = session.createQuery(hql);
			query.setParameter("generalRefId", new BigDecimal(id));
			GeneralReference projectStatus = (GeneralReference) query
					.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return projectStatus;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public GeneralReference getProjectCategory(String id) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From GeneralReference where generalRefId = :generalRefId";
			Query query = session.createQuery(hql);
			query.setParameter("generalRefId", new BigDecimal(id));
			GeneralReference projectCategory = (GeneralReference) query
					.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return projectCategory;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public BusinessDomain getProjectBussinessDomain(String id) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From BusinessDomain where domainId = :domainId";
			Query query = session.createQuery(hql);
			query.setParameter("domainId", new BigDecimal(id));
			BusinessDomain projectBussinessDomain = (BusinessDomain) query
					.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return projectBussinessDomain;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	// return false if not exist in database
	public boolean checkDuplicateProjectCode(String projectCode) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Project where code = :projectCode";
			Query query = session.createQuery(hql);
			query.setParameter("projectCode", projectCode);
			Project project = (Project) query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			if (project == null) {
				log.error("This Code not in database yet");
				return false;
			} else {
				log.error("This Code already in database");
				return true;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
