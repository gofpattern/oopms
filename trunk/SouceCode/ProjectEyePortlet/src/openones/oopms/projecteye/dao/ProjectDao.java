package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProjectDao {
    private Session session;
    private Transaction tx = null;
    private static Logger log = Logger.getLogger(CreateProjectController.class);
    
    public ProjectDao() {
       
      SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    
   public List<Project> getProjectList(String developerId) {
       try {
           session.getTransaction().begin();
           String hql = "From Project where projectId IN (Select project from Assignment WHERE developerId = :devId)";
           Query query = session.createQuery(hql);
           query.setParameter("devId", new BigDecimal(developerId));
           List<Project> projectList = query.list();               
           session.flush();
           session.getTransaction().commit();
           System.out.println("Project Name Count : "+projectList.size());
           log.error("Project Name Count : "+projectList.size());
           return projectList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   /**
    * Use to create a new project
    * @param project
    * @return
    */
   public boolean insertProject(Project project) {
	   try {
       SessionFactory sessfac = HibernateUtil.getSessionFactory();
       session = sessfac.openSession();
       tx = session.beginTransaction();
       session.save(project);
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
