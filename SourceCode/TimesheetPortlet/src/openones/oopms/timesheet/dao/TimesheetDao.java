package openones.oopms.timesheet.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import openones.oopms.timesheet.controller.TimesheetController;
import openones.oopms.timesheet.model.Assignment;
import openones.oopms.timesheet.model.Developer;
import openones.oopms.timesheet.model.Project;
import openones.oopms.timesheet.model.Timesheet;
import openones.oopms.timesheet.model.Typeofwork;
import openones.oopms.timesheet.model.Users;
import openones.oopms.timesheet.model.Workproduct;
import openones.oopms.timesheet.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;

public class TimesheetDao {
    private Session session;
  
    public TimesheetDao() {
       
      SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    private static Logger log = Logger.getLogger(TimesheetDao.class);
   public List<Project> getProjectList(String developerId) {
       try {
           log.debug("getProjectList-developerId :" +developerId);
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
  
   public List<Timesheet> getTimesheetList(String developerId, String projectId, String fromDateString, String toDateString, String status) {
       try {
           session.getTransaction().begin();
           String hql = "from Timesheet where developerId= :devId ";
           boolean projFlag = false;
           boolean fromDFlag = false;
           boolean toDFlag = false;
           boolean statusFlag = false;
          SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         
          
           if(!"All".equals(projectId)) {
               hql+="and project.projectId= :projId ";
               projFlag = true;
           }
           if(fromDateString!=null && !fromDateString.isEmpty()) {
               
               hql+="and occurDate >= :fromDate ";
               fromDFlag = true;
           }
           if(toDateString!=null && !toDateString.isEmpty()) {
               
               hql+="and occurDate <= :toDate ";
               toDFlag = true;
           }
           if(!"All".equals(status)) {               
               hql+="and status = :status";
               statusFlag = true;
           }
           BigDecimal devId = new BigDecimal(developerId);
           Query query = session.createQuery(hql);
           query.setParameter("devId", (devId));
           if(projFlag) {
               query.setParameter("projId", new BigDecimal(projectId));
           }
           if (fromDFlag) {
               Date fromDate = df.parse(fromDateString);
               query.setParameter("fromDate", fromDate);
           }
           if(toDFlag) {
               Date toDate = df.parse(toDateString);
               query.setParameter("toDate", toDate);
           }
           if(statusFlag) {
               query.setParameter("status", new BigDecimal(status));
           }
           System.out.println("query timesheet : "+hql);
          
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
   public List<openones.oopms.timesheet.model.Process> getProcessList() {
//       session.getTransaction().begin();
       String hql = "from Process order by processId";
       Query query = session.createQuery(hql);
       List<openones.oopms.timesheet.model.Process> processList = query.list();
       
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
