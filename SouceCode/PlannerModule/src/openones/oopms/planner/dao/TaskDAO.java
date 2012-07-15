package openones.oopms.planner.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

import openones.oopms.planner.model.Assignment;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.ProjectStatus;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TaskDAO {
    private Session session;
    private static Logger log = Logger.getLogger(TaskDAO.class);

    public TaskDAO() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<ProjectStatus> getAllStatus() {
        log.debug("getAllStatus.START");
        try {
            session.getTransaction().begin();
            String sql = "from ProjectStatus";
            Query query = session.createQuery(sql);
            List resultList = query.list();
            List<ProjectStatus> statusList = (List<ProjectStatus>) resultList;
            // session.flush();
            // session.getTransaction().commit();
            System.out.println("getAllStatus.end");
            return statusList;
            
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getAllStatus.ERROR", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Tasks> getAllTask() {
        log.debug("getAllTask.START");
        try {
            // session.getTransaction().begin();
            String sql = "from Tasks";
            Query query = session.createQuery(sql);
            List resultList = query.list();
            List<Tasks> taskList = (List<Tasks>) resultList;
            // session.flush();
            // session.getTransaction().commit();
            System.out.println("getAllTask.end");
            return taskList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            // Convert e.printStackTrace() to string.

            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            log.debug("getAllTask." + "exception");
            log.debug(e.getMessage());
            log.debug(stringWriter.toString());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Stage> getAllStage() {
        log.debug("getAllStage.START");
        try {
            // session.getTransaction().begin();
            String sql = "from Stage";
            Query query = session.createQuery(sql);
            List resultList = query.list();

            List<Stage> stageList = (List<Stage>) resultList;

            // session.flush();
            // session.getTransaction().commit();
            System.out.println("getAllStage.end");
            return stageList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllStage.Exception", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Process> getAllProcess() {
        log.debug("getAllProcess.START");
        try {
            // session.getTransaction().begin();
            String sql = "from Process";
            Query query = session.createQuery(sql);
            List resultList = query.list();

            List<Process> processList = (List<Process>) resultList;

            // session.flush();
            // session.getTransaction().commit();
            System.out.println("getAllProcess.end");
            return processList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllProcess.Exception", e);
        }
        return null;
    }

    // Get developers belong to project
    public List<Developer> getDeveloper(String projectId) {
        log.debug("getAssignment.START");
        try {
            // session.getTransaction().begin();
            String sql = "select developer from Assignment ass where ass.project.projectId = :projectId";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));           
            List<Developer> developerList =  query.list();

            session.flush();
            session.getTransaction().commit();
            
            System.out.println("getAssignment.end");
            return developerList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllProcess.Exception", e);
        }
        return null;
    }
}
