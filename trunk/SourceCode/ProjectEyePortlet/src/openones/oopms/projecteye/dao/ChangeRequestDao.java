package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.ChangesOfProjectPlan;
import openones.oopms.projecteye.model.Ncconstant;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ChangeRequestDao {
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

	public boolean insertChangeRequest(ChangesOfProjectPlan changeRequest) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(changeRequest);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert ko duoc");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert ngon");
		return true;
	}

	public List<ChangesOfProjectPlan> getProjectChangeRequestList(
			Project project) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From ChangesOfProjectPlan where project = :projectId";
			Query query = session.createQuery(hql);
			query.setParameter("projectId", project);
			List<ChangesOfProjectPlan> changeRequestList = query.list();
			session.getTransaction().commit();
			return changeRequestList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public Ncconstant getChangeRequestStatus(String Id) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Ncconstant where constantid = :constantid";
			Query query = session.createQuery(hql);
			query.setParameter("constantid", new BigDecimal(Id));
			Ncconstant status = (Ncconstant) query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return status;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
