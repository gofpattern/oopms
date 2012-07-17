package openones.oopms.dao;

import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import openones.oopms.model.Requirements;
import openones.oopms.model.Project;
import openones.oopms.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RequirementDAO {
    private Session session;    
    private static Logger log = Logger.getLogger(RequirementDAO.class);
    
    public RequirementDAO() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
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
    
    public void save (Requirements requirement){
        try {
        session.getTransaction().begin();
        session.save(requirement);
        session.flush();
        session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public Requirements authenticate (String username, String password){
        try {
            session.getTransaction().begin();
            String sql = "from Requirements where username = ? and password = ?";
            Query query = session.createQuery(sql);
            query.setString(0, username);
            query.setString(1, password);

            Requirements result = (Requirements)query.uniqueResult();
            session.flush();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
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

    public void update (Requirements newTask, BigDecimal id){
        try {
            session.getTransaction().begin();
            
            Requirements requirement = (Requirements)session.get(Requirements.class, id);
            //requirement.setTaskid(newTask.getTaskid());
            
            
            session.update(requirement);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete (int id){
        try {
            session.getTransaction().begin();
            session.delete(session.get(Requirements.class, id));
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}