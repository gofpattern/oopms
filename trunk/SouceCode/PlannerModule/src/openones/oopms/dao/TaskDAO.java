package openones.oopms.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import openones.oopms.model.Process;
import openones.oopms.model.Tasks;
import openones.oopms.model.Stage;
import openones.oopms.utils.HibernateUtil;

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
    public List<Tasks> getAllTask() {
        log.debug("getAllTask.START");
        try {
            session.getTransaction().begin();
            String sql = "from Tasks";
            Query query = session.createQuery(sql);
            List resultList = query.list();
            List<Tasks> taskList = (List<Tasks>) resultList;
            // session.flush();
            // session.getTransaction().commit();

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

            session.flush();
            session.getTransaction().commit();

            return processList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllProcess.Exception", e);
        }
        return null;
    }
}
