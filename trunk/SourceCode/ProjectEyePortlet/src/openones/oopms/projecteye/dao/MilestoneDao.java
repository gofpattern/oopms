package openones.oopms.projecteye.dao;

import java.math.BigDecimal;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Milestone;
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

}
