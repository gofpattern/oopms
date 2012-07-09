package openones.oopms.projecteye.dao;

import java.util.ArrayList;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.Assignment;
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
           String hql = "from Assignment where developerId= ?";
          
        //   String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
           Query query = session.createQuery(hql);
           query.setString(0, developerId);
           List<Assignment> assiList = query.list();
           List<Project> projectList = new ArrayList<Project>();  
          for(int i=0;i<assiList.size();i++) {
              projectList.add(assiList.get(i).getProject());
          }
               
           session.flush();
           session.getTransaction().commit();
           System.out.println("Project Name Count : "+projectList.size());
           return projectList;
           
       } catch (Exception e) {
          
           e.printStackTrace();
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
