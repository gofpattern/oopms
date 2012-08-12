package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.LanguageCode;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeveloperDao {
    private Session session;
    private Transaction tx = null;
    private static Logger log = Logger.getLogger(CreateProjectController.class);
    
    
   public Developer getDeveloper(String username) {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
           String hql = "From Developer WHERE UPPER(account) = :username";
           Query query = session.createQuery(hql);
           query.setParameter("username", username.toUpperCase());
           Developer developer = (Developer) query.uniqueResult();               
           session.flush();
           session.getTransaction().commit();
           return developer;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   public List<Developer> getDeveloperListForAddToProject(Project project, String searchString, String searchType) {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
    	   String hql = "";
    	   if(searchType.equals("name")) {
    		   hql = "From Developer where  NAME like :searchString and developerId not IN (Select developerId from Assignment WHERE project = :projectId and ((endDate > :currentDate) or (endDate is null)))";
    	   } else if (searchType.equals("account")) {
    		   hql = "From Developer where  account like :searchString and developerId not IN (Select developerId from Assignment WHERE project = :projectId and ((endDate > :currentDate) or (endDate is null)))";
    	   }
           Query query = session.createQuery(hql);
           query.setParameter( "searchString", "%"+searchString+"%");
           query.setParameter("projectId", project);
           query.setParameter("currentDate", new Date());
           List<Developer> developerList = query.list();               
           session.getTransaction().commit();
           return developerList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   public Developer getProjectManager (Project project) {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
           String hql = "from Developer where developerId = (select developerId from Assignment where project = :projectId and (TYPE = :PMType or TYPE = :POaPMType) and ((endDate > :currentDate) or (endDate is null)))";
           Query query = session.createQuery(hql);
           query.setParameter("projectId", project);
           query.setParameter("PMType", new BigDecimal(Constant.ProjectManagerType));
           query.setParameter("POaPMType", new BigDecimal(Constant.ProjectOwnerAndProjectManagerType));
           query.setParameter("currentDate", new Date());
           Developer developer = (Developer) query.uniqueResult();               
           session.flush();
           session.getTransaction().commit();
           return developer;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   public List<Developer> getDeveloperTeamOfProject(Project project) {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
    	   String hql = "From Developer where developerId IN (Select developerId from Assignment WHERE project = :projectId and ((endDate > :currentDate) or (endDate is null)))";
           Query query = session.createQuery(hql);
           query.setParameter("projectId", project);
           query.setParameter("currentDate", new Date());
           List<Developer> developerList = query.list();               
           session.getTransaction().commit();
           return developerList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   public boolean insertDeveloper(Developer developer) {
		try {
			SessionFactory sessfac = HibernateUtil.getSessionFactory();
			session = sessfac.openSession();
			tx = session.beginTransaction();
			session.save(developer);
			tx.commit();
			sessfac.close();
		} catch (Exception e) {
			log.error("Insert fail");
			log.error(e.getMessage());
			return false;
		}
		log.error("Insert success");
		return true;
	}

}
