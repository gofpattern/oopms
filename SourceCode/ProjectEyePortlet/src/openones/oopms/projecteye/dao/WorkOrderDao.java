package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Ncconstant;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Stage;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class WorkOrderDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public List<Stage> getStandardStageList() {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Stage where stageId <= :number";
			Query query = session.createQuery(hql);
			query.setParameter("number", new BigDecimal("6"));
			List<Stage> statusList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Issue Status Count : " + statusList.size());
			return statusList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

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

	public boolean insertStage(Milestone product) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(product);
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

	public List<Module> getUnsetDeliverableProductList(Project project) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Module where isDeliverable = :isDe and project= :projectid";
			Query query = session.createQuery(hql);
			query.setParameter("projectid", project);
			query.setParameter("isDe", new BigDecimal("0"));
			List<Module> moduleList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Module Count : " + moduleList.size());
			return moduleList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public List<Module> getSetDeliverableProductList(Project project) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Module where isDeliverable = :isDe and project= :projectid";
			Query query = session.createQuery(hql);
			query.setParameter("projectid", project);
			query.setParameter("isDe", new BigDecimal("1"));
			List<Module> moduleList = query.list();
			session.flush();
			session.getTransaction().commit();
			log.error("Module Count : " + moduleList.size());
			return moduleList;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean insertDeliverable(Module module) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			tx = session.beginTransaction();
			session.merge(module);
			tx.commit();
			sessionfactory.close();
		} catch (Exception e) {
			log.error("Insert ko duoc");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert ngon");
		return true;
	}
	
	public Module getProduct(String productID) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "From Module where moduleId = :moduleID";
			Query query = session.createQuery(hql);
			query.setParameter("moduleID", new BigDecimal(productID));
			Module product = (Module) query.uniqueResult();
			session.flush();
			session.getTransaction().commit();
			return product;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public String getStandardStageName(BigDecimal stageId) {
		try {
			SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
			session = sessionfactory.openSession();
			session.beginTransaction();
			String hql = "Select name From Stage where stageId = :stageId";
			Query query = session.createQuery(hql);
			query.setParameter("stageId", stageId);
			String stageName = (String)query.uniqueResult();
			return stageName;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	

}
