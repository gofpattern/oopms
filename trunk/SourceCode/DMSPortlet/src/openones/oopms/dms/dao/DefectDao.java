package openones.oopms.dms.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import openones.oopms.dms.util.HibernateUtil;
import openones.oopms.entity.Assignment;
import openones.oopms.entity.Defect;
import openones.oopms.entity.Developer;
import openones.oopms.entity.Project;
import openones.oopms.entity.Timesheet;


import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DefectDao {
    private Session session;
    private static Logger log = Logger.getLogger(DefectDao.class);
    public DefectDao() {
        
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    
    public List<Developer> getMemberList(String projectId) {
        try {
            log.debug("getProjectList-developerId :" + projectId);
            session.getTransaction().begin();
            String hql = "from Assignment where project.projectId= ?";
           
            Query query = session.createQuery(hql);
            query.setString(0, projectId);
            List<Assignment> assiList = query.list();
            List<BigDecimal> devListBD = new ArrayList<BigDecimal>();
            for (int i = 0; i < assiList.size(); i++) {
                devListBD.add(assiList.get(i).getDeveloperId());
            }
            DeveloperDao devDao = new DeveloperDao();
            List<Developer> devList = new ArrayList<Developer>();
            for(int i=0; i<devListBD.size();i++) {
                devList.add(devDao.getDeveloperById(devListBD.get(i)));
            }
                  
            return devList;

        } catch (Exception e) {


        }
        return null;
    }

    public List<Defect> getDefectList(String createdBy, String assignTo, String projectId, String createDate, String dueDate,String severity,
            String workProduct, String priority, String origin, String type, String status) {

        try {
            session.getTransaction().begin();
            String hql = "from Defect where projectId= :projectId ";
            boolean createFlag = false;
            boolean assiFlag = false;
            boolean projFlag = true;
            boolean severityFlag = false;
            boolean wpFlag = false;
            boolean priorFlag = false;
            boolean originFlag = false;
            boolean typeFlag = false;
            boolean crFlag = false;
            boolean duFlag = false;
            boolean statusFlag = false;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            
            
            if (!"".equals(createdBy)) {
                hql += "and createdBy= :createdBy ";
                createFlag = true;
            }
            if (!"".equals(assignTo)) {
                hql += "and assignedTo= :assignedTo ";
                assiFlag = true;
            }
            
            if (!"".equals(priority)) {
                hql += "and dpId= :priority ";
                priorFlag = true;
            }    
            if (!"".equals(workProduct)) {
                hql += "and wdId= :workProduct ";
                wpFlag = true;
            }  
            if (!"".equals(severity)) {
                hql += "and defsId= :severity ";
                severityFlag = true;
            } 
            if (!"".equals(origin)) {
                hql += "and processId= :origin ";
                originFlag = true;
            } 
            if (!"".equals(type)) {
                hql += "and dpId= :type ";
                typeFlag = true;
            } 
            if (createDate != null && !createDate.isEmpty()) {

                hql += "and createDate >= :createDate ";
                crFlag = true;
            }
            if (dueDate != null && !dueDate.isEmpty()) {

                hql += "and dueDate <= :dueDate ";
                duFlag = true;
            }
            
            if (!"".equals(status)) {
                hql += "and dsId = :status";
                statusFlag = true;
            }
         
            System.out.println("HQL" + hql);
            Query query = session.createQuery(hql);
            
             
            if (createFlag) {
                query.setParameter("createdBy", createdBy);
            }            
            if (assiFlag) {
                query.setParameter("assignedTo", assignTo);
            }            
            if (projFlag) {
                query.setParameter("projectId", new BigDecimal(projectId));
            }
            if (priorFlag) {
                query.setParameter("priority", new BigDecimal(priority));
            }
            if (typeFlag) {
                query.setParameter("type", new BigDecimal(type));
            }
            if (originFlag) {
                query.setParameter("origin", new BigDecimal(origin));
            }
            if (wpFlag) {
                query.setParameter("workProduct", new BigDecimal(workProduct));
            }
            if (severityFlag) {
                query.setParameter("severity", new BigDecimal(severity));
            }
            if (crFlag) {
                Date fromDate = df.parse(createDate);
                query.setParameter("createDate", createDate);
            }
            if (duFlag) {
                Date toDate = df.parse(dueDate);
                query.setParameter("dueDate", dueDate);
            }
            if (statusFlag) {
                query.setParameter("status", new BigDecimal(status));
            }
            System.out.println("query defect : " + hql);

            List<Defect> defList = query.list();
           
           
            return defList;

        } catch (Exception e) {

        }
        return null;
    }

    public void insertDefect(Defect defect) {
        session.getTransaction().begin();
        session.save(defect);
        session.flush();
        session.getTransaction().commit();
        
    }
    public BigDecimal getNextDefect() {
        try {
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            session = sessionfactory.openSession();
            session.beginTransaction();
            String sql = "SELECT DEFECT_SEQ.NEXTVAL as nextValue FROM dual";
            Query query = session.createSQLQuery(sql).addScalar("nextValue", Hibernate.BIG_DECIMAL);
            BigDecimal nextId = (BigDecimal) query.list().get(0);

            return nextId;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
    public Defect getDefectById(BigDecimal id) {
        try {
            session.getTransaction().begin();
            String hql = "from Defect where defectId =:prId";
            Query query = session.createQuery(hql);
            query.setParameter("prId", id);           
            Defect defect = (Defect) query.uniqueResult();
            session.flush();
            return defect;
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return null;
        }
    }

    public void updateDefect(Defect defect) throws HibernateException, ParseException {
       
        String hql = "UPDATE Defect df SET df.projectId =:projectId, df.createdBy =:createdBy,"
            +"df.title =:title, df.description =:description, df.qaId=:qaId, df.processId=:processId,"
            +"df.dtId =:dtId, dt.dpId =:dpId, df.wpId =:wpId, df.defsId =:defsId,"
            +"df.testCase =:testCase, df.defectOwner=:defectOwner, df.assignedTo =:assignedTo,"
            +"df.createDate =:createDate, df.dueDate =:dueDate, df.causeAnalysis =:causeAnalysis,"
            +"df.solution =:solution";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        session.getTransaction().begin();
        Query query = session.createQuery(hql);
        query.setParameter("projectId", defect.getProjectId());
        query.setParameter("createdBy", defect.getCreatedBy());
        query.setParameter("title",defect.getTitle());
        query.setParameter("description", defect.getDescription());
        query.setParameter("qaId", defect.getQaId());
        query.setParameter("processId", defect.getProcessId());
        query.setParameter("testCase", defect.getTitle());
        query.setParameter("defectOwner", new Date());
        query.setParameter("assignedTo", defect.getAssignedTo());
        query.setParameter("createDate", sdf.parse(defect.getCreateDateString()));
        query.setParameter("dueDate", sdf.parse(defect.getDueDateString()));
        query.setParameter("causeAnalysis", defect.getCauseAnalysis());
        query.setParameter("solution", defect.getSolution());

        int rowCount = query.executeUpdate();
        session.flush();
        session.getTransaction().commit();
        
                       
    }
    
    
}
