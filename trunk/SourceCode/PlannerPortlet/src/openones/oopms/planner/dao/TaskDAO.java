package openones.oopms.planner.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.GeneralReference;
import openones.oopms.planner.model.Language;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.model.Workproduct;
import openones.oopms.planner.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TaskDAO {
    private Session session;
    private static Logger log = Logger.getLogger(TaskDAO.class);

    public TaskDAO() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();

    }

    @SuppressWarnings("unchecked")
    public List<GeneralReference> getProjectStatusEn() {
        log.debug("getProjectStatusEn.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from GeneralReference where groupCode = 'PROJECT_STATUS' and languageCode.langCode  = 'en'";
            Query query = session.createQuery(sql);
            List<GeneralReference> statusList = query.list();

            return statusList;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getProjectStatusEn.ERROR", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Tasks> getAllTask() {
        log.debug("getAllTask.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from Tasks";
            Query query = session.createQuery(sql);
            List<Tasks> taskList = query.list();

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

    public List<Tasks> getTasksByProjectId(String projectId) {
        log.debug("getTaskByProjectId.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            session.clear();
            String sql = "from Tasks where projectid = :projectId and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            @SuppressWarnings("unchecked")
            List<Tasks> taskList = query.list();

            return taskList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.debug("getTaskByProjectId.Error", e);
        }
        return null;
    }

    public Tasks getTaskById(BigDecimal taskId) {
        log.debug("getTaskById.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            session.clear();
            Tasks task = (Tasks) session.get(Tasks.class, taskId);

            return task;
        } catch (Exception e) {
            log.error("getTaskById.Exception", e);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public List<Stage> getAllStage() {
        log.debug("getAllStage.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from Stage";
            Query query = session.createQuery(sql);
            List<Stage> stageList = query.list();

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
    public List<Project> getAllProject() {
        log.debug("getAllProject.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from Project";
            Query query = session.createQuery(sql);
            List<Project> projectList = query.list();
            return projectList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllStage.Exception", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Workproduct> getAllProduct() {
        log.debug("getAllProduct.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from Workproduct";
            Query query = session.createQuery(sql);
            List<Workproduct> productList = query.list();
            return productList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllProduct.Exception", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Process> getAllProcess() {
        log.debug("getAllProcess.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from Process";
            Query query = session.createQuery(sql);
            List<Process> processList = query.list();

            return processList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllProcess.Exception", e);
        }
        return null;
    }

    /**
     * Get developers belong to project.
     * @param projectId
     * @return
     */
    public List<Developer> getDeveloper(String projectId) {
        log.debug("getDeveloper.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "select developer from Assignment ass where ass.project.projectId = :projectId";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            @SuppressWarnings("unchecked")
            List<Developer> developerList = query.list();
            return developerList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getDeveloper.Exception", e);
        }
        return null;
    }

    /**
     * add new task to DB
     * @param task
     */
    public void addTask(Tasks task) {
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            factory.close();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Add Task Error", e);
        }
    }

    /**
     * delete task
     * @param id of task
     */
    public void deleteTask(BigDecimal id) {
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            Transaction tx = session.beginTransaction();
            Tasks task = (Tasks) session.get(Tasks.class, id);
            task.setActive(false);
            session.update(task);
            tx.commit();
            factory.close();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete Task Error", e);
        }
    }

    /**
     * update Task
     * @param editTask: new value of current task
     * @param id: id of current task
     */
    public void updateTask(Tasks editTask) {
        log.debug("updateTask.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            Transaction tx = session.beginTransaction();
            if (editTask.getStatusid().equals(new BigDecimal(174))) {
                Calendar cal = Calendar.getInstance();
                editTask.setActualDate(cal.getTime());
                editTask.setEffort(editTask.getCurrenteffort());
            }
            session.merge(editTask);
            tx.commit();
            factory.close();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update Task Error", e);
        }
    }

    public List<Language> getSizeUnit() {
        log.debug("getSizeUnit.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
            String sql = "from Language";
            Query query = session.createQuery(sql);
            @SuppressWarnings("unchecked")
            List<Language> sizeUnitList = query.list();
            return sizeUnitList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getSizeUnit.Exception", e);
        }
        return null;
    }
}
