package openones.oopms.projecteye.dao;

import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.DefectPriority;
import openones.oopms.projecteye.model.Ncconstant;
import openones.oopms.projecteye.model.Process;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Risk;
import openones.oopms.projecteye.model.RiskCategory;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class IssueDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public List<Ncconstant> getStatusList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Ncconstant where type = :stype";
			Query query = session.createQuery(hql);
			query.setParameter("stype", "Status");
			List<Ncconstant> statusList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Issue Status Count : " + statusList.size());
			return statusList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<DefectPriority> getPriorityList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From DefectPriority";
			Query query = session.createQuery(hql);
			List<DefectPriority> priorityList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Issue Priority Count : " + priorityList.size());
			return priorityList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<Process> getProcessList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Process";
			Query query = session.createQuery(hql);
			List<Process> processList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Issue Process Count : " + processList.size());
			return processList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<RiskCategory> getTypeList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From RiskCategory where isNew = :iNew";
			Query query = session.createQuery(hql);
			query.setParameter("iNew", new Byte("1"));
			List<RiskCategory> typeList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Issue Type Count : " + typeList.size());
			return typeList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	// public boolean insertIssue(Issue issue) {
	// try {
	// SessionFactory sessfac = HibernateUtil.getSessionFactory();
	// session = sessfac.openSession();
	// tx = session.beginTransaction();
	// session.save(issue);
	// tx.commit();
	// sessfac.close();
	// } catch (Exception e) {
	// log.error("Insert deo duoc");
	// log.error(e.getMessage());
	// return false;
	// }
	// log.error("Insert ngon");
	// return true;
	// }

	public List<Risk> getProjectRiskList(Project project) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Risk where project = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", project);
			List<Risk> riskList = query.list();
			session.getTransaction().commit();
			return riskList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
