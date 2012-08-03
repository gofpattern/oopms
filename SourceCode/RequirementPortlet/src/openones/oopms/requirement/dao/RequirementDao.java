package openones.oopms.requirement.dao;

import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import openones.oopms.requirement.model.Project;
import openones.oopms.requirement.model.Requirements;
import openones.oopms.requirement.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RequirementDAO {
    private Session session;    
    private Transaction tx = null;
    private static Logger log = Logger.getLogger(RequirementDAO.class);
    
    public RequirementDAO() {
        log.debug("New RequirementDao");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        log.debug("Get hibernate Session");
        this.session = factory.getCurrentSession();
        log.debug("Get current Session");
    }            
    
    public boolean updateReq(Requirements req) {
        log.debug("updateReqDAO");
        int rowCount = 0;
        try {
            SessionFactory sessfac = HibernateUtil.getSessionFactory();
            session = sessfac.openSession();
            tx = session.beginTransaction();      
            
            log.error("UpdateContent: "+req.getRequirementID()+", "+req.getRequirement()+", "+req.getProjectID()+", "+req.getReleaseNote());
            session.update(req);            
            
//            String hql = "UPDATE Requirements requirement SET requirement.project_ID =:project_ID, requirement.type =:type,"
//                    + "requirement.srs =:srs, requirement.dd=:dd, requirement.testcase= :testcase, requirement.release_note =:release_note, requirement.requirement =:requirement,"
//                    + "requirement.code_module =:code_module, requirement.effort =:effort, requirement.elapsed_day =:elapsed_day, requirement.req_size =:req_size,"
//                    + "requirement.response_date =:response_date, requirement.create_date =:create_date, requirement.committed_date =:committed_date, requirement.designed_date =:designed_date,"
//                    + "requirement.coded_date =:coded_date, requirement.tested_date =:tested_date, requirement.cancelled_date =:cancelled_date, requirement.accepted_date =:accepted_date, requirement.deployed_date =:deployed_date"
//                    + "WHERE requirement.requirement_ID =:requirement_ID";
//
//            //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//            
//                Query query = session.createQuery(hql);
//                query.setParameter("requirement_ID", req.getRequirementID());
//                query.setParameter("project_ID", req.getProjectID());                
//                query.setParameter("type", req.getType());
//                query.setParameter("srs", req.getSrs());                
//                query.setParameter("dd", req.getDd());
//                query.setParameter("testcase", req.getTestcase());                
//                query.setParameter("release_note", req.getReleaseNote());
//                query.setParameter("requirement", req.getRequirement());                
//                query.setParameter("code_module", req.getCodeModule());
//                query.setParameter("effort", req.getEffort());                
//                query.setParameter("elapsed_day", req.getElapsedDay());
//                query.setParameter("response_date", req.getResponseDate());                
//                query.setParameter("create_date", req.getCreateDate());
//                query.setParameter("committed_date", req.getCommmitedDate());                
//                query.setParameter("designed_date", req.getDesignedDate());
//                query.setParameter("coded_date", req.getCodedDate());                
//                query.setParameter("tested_date", req.getTestedDate());
//                query.setParameter("cancelled_date", req.getCancelledDate());                
//                query.setParameter("accepted_date", req.getAcceptedDate());
//                query.setParameter("deployed_date", req.getDeployedDate());                
//                query.setParameter("req_size", req.getReqSize());                
                //query.setParameter("occurDate", sdf.parse(timesheetList.get(i).getOccurDateString()));                

//                rowCount = query.executeUpdate();                           
                       
            session.flush();                        
            tx.commit();
            sessfac.close();
            log.error("UpdateOk: "+rowCount);
        } catch (Exception e) {
            log.error("UpdateNOTOk: "+rowCount);
            log.error(e.getMessage());
            return false;
        }   
        log.error("UpdateOk: "+rowCount);
        return true;                                        
    }
    
    public Requirements getReq(String reqID) throws ParseException {

        try {
            Requirements req;
            session.getTransaction().begin();
            String sql = "from Requirements WHERE requirement_ID =:reqId";           
            Query query = session.createQuery(sql);                        
            query.setParameter("reqId", reqID);            
            log.debug("createGetRequirementbyIDQuery: "+ query.toString() );
            req = (Requirements) query.uniqueResult();            
            log.debug("dcminracoi: "+req.getRequirement());
            return req;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            // Convert e.printStackTrace() to string.
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            log.debug("errorgetAllRequirement." + "exception");
            log.debug(e.getMessage());
            log.debug(stringWriter.toString());
        }
        return null;
    }
    
    public void deleteReq(List<Requirements> requirementList) throws ParseException {

        try {

            session.getTransaction().begin();
            String hql = "DELETE Requirements WHERE requirement_ID =:reqId";

            for (int i = 0; i < requirementList.size(); i++) {
                Query query = session.createQuery(hql);
                query.setParameter("reqId", requirementList.get(i).getRequirementID());
                int rowCount = query.executeUpdate();
                System.out.println("Rows affected: " + rowCount);
                log.debug("Rows affected: " + rowCount);
                log.debug("DeleteReqOk");
            }
            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            log.debug("DeleteReqError");
            // session.getTransaction().rollback();
            // session.close();

        }
    }
    
    /**
     * Use to create a new project
     * 
     * @param project
     * @return
     */
    public boolean insertReq(Requirements req) {
        log.debug("insertReqDAO");
        try {
            SessionFactory sessfac = HibernateUtil.getSessionFactory();
            session = sessfac.openSession();
            tx = session.beginTransaction();
            session.save(req);
            session.flush();                        
            tx.commit();
            sessfac.close();
        } catch (Exception e) {
            log.error("Insert not Ok");
            log.error(e.getMessage());
            return false;
        }
        log.error("Insert Ok");
        return true;
    }
    
    @SuppressWarnings("unchecked")
    public List<Requirements> getAllRequirement() {
        log.debug("getAllRequirementSTART");
        try {
            session.getTransaction().begin();
            String sql2 = "select requirement_ID, project_ID, type from Requirements";
            String sql = "from Requirements";
            Query query = session.createQuery(sql);
            log.debug("createGetAllRequirementQuery: "+ query.toString() );
            List resultList = query.list();
            log.debug("resultlistRequirement: "+  resultList.size());
            List<Requirements> requirementList = (List<Requirements>) resultList;
//            session.flush();
//            session.getTransaction().commit();
            log.debug("dcminracoi: "+requirementList.size());
            return requirementList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            // Convert e.printStackTrace() to string.
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            log.debug("errorgetAllRequirement." + "exception");
            log.debug(e.getMessage());
            log.debug(stringWriter.toString());
        }
        return null;
    }
    
    public List<Project> getAllProject() {
        log.debug("getAllProjectSTART");
        try {
            session.getTransaction().begin();
            String sql2 = "select requirement_ID, project_ID, type from Requirements";
            String sql = "from Project";
            Query query = session.createQuery(sql);
            log.debug("createGetAllProjectQuery: "+ query.toString() );
            List resultList = query.list();
            log.debug("resultlistProject: "+  resultList.size());
            List<Project> projectList = (List<Project>) resultList;
            session.flush();
            session.getTransaction().commit();
            log.debug("dcminracoi: "+projectList.size());
            return projectList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            // Convert e.printStackTrace() to string.
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            log.debug("errorgetAllRequirement." + "exception");
            log.debug(e.getMessage());
            log.debug(stringWriter.toString());
        }
        return null;
    }         

    public Requirements[] findByLastName (String lastname){
        try {
            session.getTransaction().begin();
            String sql = "from Requirements where lastname like ?";
            Query query = session.createQuery(sql);
            query.setString(0, "%" + lastname + "%");
            List resultList = query.list();
            Requirements[] taskArray = new Requirements[resultList.size()];
            resultList.toArray(taskArray);
            session.flush();
            session.getTransaction().commit();
            return taskArray;
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return null;
    }        

}
