package openones.oopms.planner.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.model.ProjectStatus;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.model.Workproduct;
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
            List<ProjectStatus> statusList = query.list();
            session.flush();
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
            session.getTransaction().begin();
            String sql = "from Tasks";
            Query query = session.createQuery(sql);
            List<Tasks> taskList = query.list();
            session.flush();
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
    
    public List<Tasks> getTasksByProjectId(String projectId) {
        log.debug("getTaskByProjectId.START");
        try {
            session.getTransaction().begin();
            String sql = "from Tasks where projectid = :projectId and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            @SuppressWarnings("unchecked")
            List<Tasks> taskList = query.list();
            session.flush();
            System.out.println("getTaskByProjectId.end");
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
            session.getTransaction().begin();
            String sql = "from Tasks where taskid = :taskId";
            Query query = session.createQuery(sql);
            query.setParameter("taskId", taskId);
            Tasks task = (Tasks) query.uniqueResult();
            session.flush();            
            System.out.println("getTaskById.end");
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
            session.getTransaction().begin();
            String sql = "from Stage";
            Query query = session.createQuery(sql);
            List<Stage> stageList = query.list();
            session.flush();            
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
    public List<Project> getAllProject() {
        log.debug("getAllProject.START");
        try {
            session.getTransaction().begin();
            String sql = "from Project";
            Query query = session.createQuery(sql);
            List<Project> projectList = query.list();
            session.flush();
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
            session.getTransaction().begin();
            String sql = "from Workproduct";
            Query query = session.createQuery(sql);
            List<Workproduct> productList = query.list();
            session.flush();
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
            session.getTransaction().begin();
            String sql = "from Process";
            Query query = session.createQuery(sql);
            List<Process> processList = query.list();
            session.flush();
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

    /**
     * Get developers belong to project.
     * @param projectId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Developer> getDeveloper(String projectId) {
        log.debug("getDeveloper.START");
        try {
            session.getTransaction().begin();
            String sql = "select developer from Assignment ass where ass.project.projectId = :projectId";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            List<Developer> developerList = query.list();
            session.flush();
            System.out.println("getAssignment.end");
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
            session.getTransaction().begin();
            task.setActive(true);
            session.save(task);
            session.flush();
            session.getTransaction().commit();
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
            session.getTransaction().begin();
            Tasks task = (Tasks) session.get(Tasks.class, id);
            task.setActive(false);
            // session.delete(session.get(Tasks.class, id));
            session.update(task);
            session.flush();
            session.getTransaction().commit();
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
            session.getTransaction().begin();
            // Tasks task = (Tasks) session.get(Tasks.class, id);
            if (editTask.getStatusid().equals(new BigDecimal(2))) {
                // not sure
                Calendar cal = Calendar.getInstance();
                editTask.setActualDate(cal.getTime());
                editTask.setEffort(editTask.getCurrenteffort());
            }
            session.update(editTask);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update Task Error", e);
        }
    }

}
