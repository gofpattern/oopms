package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MilestoneDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public Milestone getStage(String stageId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Milestone where milestoneId = :milestoneId";
			Query query = session.createQuery(hql);
			query.setParameter("milestoneId", new BigDecimal(stageId));
			Milestone stage = (Milestone) query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return stage;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<Milestone> getProjectStage(Project project) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Milestone where project = :project";
			Query query = session.createQuery(hql);
			query.setParameter("project", project);
			List<Milestone> projectStages = query.list();
			session.flush();
			session.getTransaction().commit();
			return projectStages;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean updateStage(Milestone stage) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.merge(stage);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Update Fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Update Sucess");
		return true;
	}
}
