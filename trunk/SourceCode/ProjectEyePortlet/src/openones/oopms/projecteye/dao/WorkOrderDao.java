package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Ncconstant;
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
            log.error("Issue Status Count : "+statusList.size());
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
            log.error("Issue Status Count : "+statusList.size());
            return statusList;
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
