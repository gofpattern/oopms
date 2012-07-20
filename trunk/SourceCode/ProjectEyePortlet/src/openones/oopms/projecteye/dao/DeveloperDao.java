package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.LanguageCode;
import openones.oopms.projecteye.model.Project;
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
           String hql = "From Developer WHERE account = :username";
           Query query = session.createQuery(hql);
           query.setParameter("username", username);
           Developer developer = (Developer) query.uniqueResult();               
           session.flush();
           session.getTransaction().commit();
           return developer;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   

}
