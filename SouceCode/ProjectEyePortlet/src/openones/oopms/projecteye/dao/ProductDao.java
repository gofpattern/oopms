package openones.oopms.projecteye.dao;

import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Workproduct;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductDao {
    private Session session;
    private Transaction tx = null;
    private static Logger log = Logger.getLogger(CreateProjectController.class);
        
    public List<Workproduct> getWorkProductList() {
        try {
     	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
     	   session = sessionfactory.openSession();
     	   session.beginTransaction();
            String hql = "From Workproduct";
            Query query = session.createQuery(hql);
            List<Workproduct> workProductList = query.list();               
            session.flush();
            session.getTransaction().commit();
            log.error("workProductList Count : "+workProductList.size());
            return workProductList;
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    
    public List<Language> getProductSizeUnitList() {
        try {
     	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
     	   session = sessionfactory.openSession();
     	   session.beginTransaction();
            String hql = "From Language";
            Query query = session.createQuery(hql);
            List<Language> productSizeUnitList = query.list();               
            session.flush();
            session.getTransaction().commit();
            log.error("productSizeUnitList Count : "+productSizeUnitList.size());
            return productSizeUnitList;
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
