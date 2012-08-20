package openones.oopms.projecteye.dao;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Workunit;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class WorkUnitDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);

	public boolean insertWorkUnit(Workunit workunit) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(workunit);
			session.flush();
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert deo duoc");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert ngon");
		return true;
	}
}
