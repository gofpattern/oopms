package openones.oopms.projecteye.dao;

import java.util.ArrayList;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Risk;
import openones.oopms.projecteye.model.RiskSource;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RiskDao {
    private Session session;
    private Transaction tx = null;
    private static Logger log = Logger.getLogger(CreateProjectController.class);
    
    public RiskDao() {
       
      SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    
   public ArrayList<RiskSource> getRiskSourceList() {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();

           List list = session.createQuery("from openones.oopms.projecteye.model.RiskSource").list();
           ArrayList<RiskSource> rList = new ArrayList<RiskSource>();
           for (int i = 0; i < list.size(); ++i) {
        	   rList.add((RiskSource) list.get(i));
           }
           log.error("dang lay");
           return rList;
       } catch (Exception e) {
    	   log.error("lay deo duoc");
    	   log.error(e.getMessage());
       }
       return null;
   }
   
   /**
    * Use to create a new Risk
    * @param project
    * @return
    */
   public boolean insertRisk(Risk risk) {
	   try {
       SessionFactory sessfac = HibernateUtil.getSessionFactory();
       session = sessfac.openSession();
       tx = session.beginTransaction();
       session.save(risk);
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
