package openones.oopms.planner.dao;

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

    /**
     * Get list status string.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GeneralReference> getProjectStatusEn() {
        log.debug("getProjectStatusEn.START");
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "from GeneralReference where groupCode = 'PROJECT_STATUS' and languageCode.langCode  = 'en'";
            Query query = session.createQuery(sql);
            List<GeneralReference> statusList = query.list();
            tx.commit();
            return statusList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getProjectStatusEn.ERROR", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }

    /**
     * Get all tasks from DB.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Tasks> getAllTask() {
        log.debug("getAllTask.START");
        try {
            session.getTransaction().begin();
            String sql = "from Tasks";
            Query query = session.createQuery(sql);
            List<Tasks> taskList = query.list();

            return taskList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.debug("getAllTask.Error", e);

        }
        return null;
    }

    /**
     * Get All tasks belong to a project.
     * @param projectId
     * @return
     */
    public List<Tasks> getTasksByProjectId(String projectId) {
        log.debug("getTaskByProjectId.START");
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.clear();
            String sql = "from Tasks where projectid = :projectId and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            @SuppressWarnings("unchecked")
            List<Tasks> taskList = query.list();
            tx.commit();
            return taskList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getTaskByProjectId.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
    
    public List<Tasks> getTasksByDeveloperOfProject(String projectId, String developerId) {
        log.debug("getTaskByProjectId.START");
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.clear();
            String sql = "from Tasks where projectid = :projectId and assignedto = :developerId and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            query.setParameter("developerId", new BigDecimal(developerId));
            @SuppressWarnings("unchecked")
            List<Tasks> taskList = query.list();
            tx.commit();
            return taskList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getTaskByProjectId.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
    
    /**
     * Get task by Id.
     * @param taskId
     * @return
     */
    public Tasks getTaskById(BigDecimal taskId) {
        log.debug("getTaskById.START");

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.clear();
            Tasks task = (Tasks) session.get(Tasks.class, taskId);
            tx.commit();
            return task;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getTaskById.ERROR", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
    /**
     * Get all stages which are defined in DB.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Stage> getAllStage() {
        log.debug("getAllStage.START");
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "from Stage";
            Query query = session.createQuery(sql);
            List<Stage> stageList = query.list();
            tx.commit();
            return stageList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getAllStage.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Get all projects in DB.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Project> getAllProject() {
        log.debug("getAllProject.START");
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "from Project";
            Query query = session.createQuery(sql);
            List<Project> projectList = query.list();
            tx.commit();
            return projectList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getAllProject.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Get all work products are defined in DB.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Workproduct> getAllProduct() {
        log.debug("getAllProduct.START");

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "from Workproduct";
            Query query = session.createQuery(sql);
            List<Workproduct> productList = query.list();
            tx.commit();
            return productList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getAllProduct.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Get All processes are defined in DB.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Process> getAllProcess() {
        log.debug("getAllProcess.START");

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String sql = "from Process";
            Query query = session.createQuery(sql);
            List<Process> processList = query.list();
            tx.commit();
            return processList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getAllProcess.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    /**
     * Get developers belong to project.
     * @param projectId
     * @return
     */
    public List<Developer> getDeveloper(String projectId) {
        log.debug("getDeveloper.START");
        try {
            session.getTransaction().begin();
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
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(task);
            tx.commit();
        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("Add Task Error", e);
        } finally {
            if (session != null) {
                session.close();
            }

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
            session.update(task);
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
            if (editTask.getStatusid().equals(new BigDecimal(174))) {
                Calendar cal = Calendar.getInstance();
                editTask.setActualDate(cal.getTime());
                editTask.setEffort(editTask.getCurrenteffort());
            }
            session.merge(editTask);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update Task Error", e);
        }
    }

    /**
     * Get all languages are defined in DB.
     * @return
     */
    public List<Language> getSizeUnit() {
        log.debug("getSizeUnit.START");
        try {
            session.getTransaction().begin();
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
    /**
     * Update plan effort and actual effort after adding new task.
     * @param task
     */
    public void updateProjectEffortTask(Tasks task) {
        log.debug("updateProjectEffortTask.START");
        Session session = null;
        Transaction tx = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Project project = (Project) session.get(Project.class, task.getProjectid());

            project.setPlanEffort(task.getPlannedeffort());
            project.setActualEffort(task.getCurrenteffort());

            session.merge(project);
            tx.commit();
        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("updateProjectEffortTask.ERROR", e);
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }
    /**
     * Update plan effort and actual effort after updating a task.
     * @param task
     * @param editedTask
     */
    public void updateProjectEffortByEditedTask(Tasks task, Tasks editedTask) {
        log.debug("updateProjectEffortByEditedTask.START");
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Project project = (Project) session.get(Project.class, editedTask.getProjectid());

            if (!task.getPlannedeffort().equals(editedTask.getPlannedeffort())) {
                project.setPlanEffort(project.getPlanEffort().add(
                        editedTask.getPlannedeffort().subtract(task.getPlannedeffort())));
            }
            if (!task.getCurrenteffort().equals(editedTask.getCurrenteffort())) {
                project.setActualEffort(project.getActualEffort().add(
                        editedTask.getCurrenteffort().subtract(task.getCurrenteffort())));
            }

            session.merge(project);
            tx.commit();
        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("updateProjectEffortByEditedTask.ERROR", e);
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }

    /**
     * Get project by Id.
     * @param projectId
     * @return
     */
    public Project getProjectById(BigDecimal projectId) {
        log.debug("getProjectById.START");
        try {
            session.getTransaction().begin();
            Project project = (Project) session.get(Project.class, projectId);
            return project;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getProjectById.ERROR", e);
        }
        return null;
    }
    
    /**
     * Get number of Closed tasks belong to user in project.
     * @param projectId
     * @param developerId
     * @return
     */
    public long getNumberOfClosedTask(String projectId, String developerId) {
        log.debug("getNumberOfClosedTask.START");
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "Select count(*) from Tasks where assignedto = :developerId " +
            		"and projectid = :projectId and statusid = 174 and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            query.setParameter("developerId", new BigDecimal(developerId));            
            long numberOfClosed = (Long) query.uniqueResult();
            tx.commit();
            return numberOfClosed;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getNumberOfClosedTask.ERROR", e);
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }
    /**
     * Get number of Cancelled tasks belong to user in project.
     * @param projectId
     * @param developerId
     * @return
     */
    public long getNumberOfCancelledTask(String projectId, String developerId) {
        log.debug("getNumberOfOngoingTask.START");
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "Select count(*) from Tasks where assignedto = :developerId " +
                    "and projectid = :projectId and statusid = 175 and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            query.setParameter("developerId", new BigDecimal(developerId));            
            long numberOfCancelled = (Long) query.uniqueResult();
            tx.commit();
            return numberOfCancelled;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getNumberOfOngoingTask.ERROR", e);
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }
    /**
     * Get number of Tentative tasks belong to user in project.
     * @param projectId
     * @param developerId
     * @return
     */
    public long getNumberOfTentativeTask(String projectId, String developerId) {
        log.debug("getNumberOfTentativeTask.START");
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "Select count(*) from Tasks where assignedto = :developerId " +
                    "and projectid = :projectId and statusid = 176 and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            query.setParameter("developerId", new BigDecimal(developerId));            
            long numberOfTentative = (Long) query.uniqueResult();
            tx.commit();
            return numberOfTentative;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getNumberOfTentativeTask.ERROR", e);
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }
    /**
     * Get number of On-going tasks belong to user in project.
     * @param projectId
     * @param developerId
     * @return
     */
    public long getNumberOfOngoingTask(String projectId, String developerId) {
        log.debug("getNumberOfOngoingTask.START");
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "Select count(*) from Tasks where assignedto = :developerId " +
                    "and projectid = :projectId and statusid = 173 and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", new BigDecimal(projectId));
            query.setParameter("developerId", new BigDecimal(developerId));            
            long numberOfOngoing = (Long) query.uniqueResult();
            tx.commit();
            return numberOfOngoing;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getNumberOfOngoingTask.ERROR", e);
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }
    
}
