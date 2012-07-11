package openones.oopms.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import openones.oopms.model.Assignment;
import openones.oopms.model.Developer;
import openones.oopms.model.Project;
import openones.oopms.model.Timesheet;
import openones.oopms.model.Typeofwork;
import openones.oopms.model.Users;
import openones.oopms.model.Workproduct;
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
//               
//           session.flush();
//           session.getTransaction().commit();
           System.out.println("Project Name Count : "+projectList.size());
           return projectList;
           
       } catch (Exception e) {
          
           e.printStackTrace();
       }
       return null;
   }
  
   public List<Timesheet> getTimesheetList(String developerId, String projectId, String fromDate, String toDate, String status) {
       try {
           session.getTransaction().begin();
           String hql = "from Timesheet where developerId= :devId";
           boolean projFlag = false;
           boolean fromDFlag = false;
           boolean toDFlag = false;
           boolean statusFlag = false;
           
           if(!"All".equals(projectId)) {
               hql+="and projectId= :projId";
               projFlag = true;
           }
           if(!fromDate.isEmpty()) {
               hql+="and occurDate >= :fromDate";
               fromDFlag = true;
           }
           if(!toDate.isEmpty()) {
               hql+="and occurDate <= :toDate";
               toDFlag = true;
           }
           if(!status.isEmpty()) {
               hql+="and status = :status";
               statusFlag = true;
           }
           BigDecimal devId = new BigDecimal(developerId);
           Query query = session.createQuery(hql);
           query.setParameter("devId", (devId));
           if(projFlag) {
               query.setParameter("projId", projectId);
           }
           if (fromDFlag) {
               query.setParameter("fromDate", fromDate);
           }
           if(toDFlag) {
               query.setParameter("toDate", toDate);
           }
           if(statusFlag) {
               query.setParameter("status", status);
           }
          
           List<Timesheet> timesheetList = query.list();
         
//           session.flush();
//           session.getTransaction().commit();
           System.out.println("Timesheet Record : "+timesheetList.size());
           return timesheetList;
           
       } catch (Exception e) {
          
           e.printStackTrace();
       }
       return null;
   }
   public List<openones.oopms.model.Process> getProcessList() {
//       session.getTransaction().begin();
       String hql = "from Process order by processId";
       Query query = session.createQuery(hql);
       List<openones.oopms.model.Process> processList = query.list();
       
//       session.flush();
//       session.getTransaction().commit();
       System.out.println("Process Record : "+processList.size());
       return processList;
   }
   
   public List<Typeofwork> getTOWList() {
//     session.getTransaction().begin();
     String hql = "from Typeofwork order by towId";
     Query query = session.createQuery(hql);
     List<Typeofwork> towList = query.list();
     
//     session.flush();
//     session.getTransaction().commit();
     System.out.println("towList Record : "+towList.size());
     return towList;
 }
   
   public List<Workproduct> getWorkProductList() {
//     session.getTransaction().begin();
     String hql = "from Workproduct order by wpId";
     Query query = session.createQuery(hql);
     List<Workproduct> workProductList = query.list();
     
     session.flush();
     session.getTransaction().commit();
     System.out.println("workProductList Record : "+workProductList.size());
     return workProductList;
 }
}
