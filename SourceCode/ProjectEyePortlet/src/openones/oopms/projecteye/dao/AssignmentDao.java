package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workproduct;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AssignmentDao {
	private Session session;
	private Transaction tx = null;
	private static Logger log = Logger.getLogger(CreateProjectController.class);


	public boolean insertAssigment(Assignment assignment) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(assignment);
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
	
	public Assignment getUserRole(Project project, BigDecimal developerId) {
		 try {
	    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
	    	   session = sessionfactory.openSession();
	    	   session.beginTransaction();
	    	   String hql = "From Assignment where developerId = :developerId and project= :projectId and endDate is null ";
	           Query query = session.createQuery(hql);
	           query.setParameter("developerId", developerId);
	           query.setParameter("projectId", project);
	           Assignment role = (Assignment)query.uniqueResult();            
	           session.getTransaction().commit();
	           log.error("role la : " + role);
	           return role;
	           
	       } catch (Exception e) {
	           log.error(e.getMessage());
	       }
	       return null;
	}
	
	

}
