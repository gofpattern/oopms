package openones.oopms.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import openones.oopms.model.Assignment;
import openones.oopms.model.Developer;
import openones.oopms.model.Project;
import openones.oopms.model.Timesheet;
import openones.oopms.model.Users;
import openones.oopms.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TimesheetDao {
    private Session session;
  
    public TimesheetDao() {
       
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
  
//   public List<Timesheet> getTimesheetList(String developerId, String projectId, String fromDate, String toDate, String status) {
//       try {
//           session.getTransaction().begin();
//           String hql = "from Timesheet where developerId= ?";
//           if("All".equals(projectId)) {
//               hql+="and projectId= ?";
//           }
//           if(!fromDate.isEmpty()) {
//               hql+="and occurDate >= fromDate";
//           }
//           if(!toDate.isEmpty()) {
//               hql+="and occurDate <= toDate";
//           }
//                     
//       
//           Query query = session.createQuery(hql);
//           query.setString(0, developerId);
//           List<Assignment> assiList = query.list();
//           List<Project> projectList = new ArrayList<Project>();  
//          for(int i=0;i<assiList.size();i++) {
//              projectList.add(assiList.get(i).getProject());
//          }
//               
//           session.flush();
//           session.getTransaction().commit();
//           System.out.println("Project Name Count : "+projectList.size());
//           return projectList;
//           
//       } catch (Exception e) {
//          
//           e.printStackTrace();
//       }
//       return null;
//   }
}
