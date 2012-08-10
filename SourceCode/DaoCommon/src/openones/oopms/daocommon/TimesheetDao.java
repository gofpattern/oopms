package openones.oopms.daocommon;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import openones.oopms.entity.Assignment;
import openones.oopms.entity.Project;
import openones.oopms.entity.Timesheet;
import openones.oopms.entity.Typeofwork;
import openones.oopms.entity.Workproduct;
import openones.oopms.daocommon.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TimesheetDao {
    private Session session;

    public TimesheetDao() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }

    private static Logger log = Logger.getLogger(TimesheetDao.class);

    public List<Project> getProjectList(String developerId) {
        try {
            log.debug("getProjectList-developerId :" + developerId);
            session.getTransaction().begin();
            String hql = "from Assignment where developerId= ?";

            // String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
            Query query = session.createQuery(hql);
            query.setString(0, developerId);
            List<Assignment> assiList = query.list();
            List<Project> projectList = new ArrayList<Project>();
            for (int i = 0; i < assiList.size(); i++) {
                projectList.add(assiList.get(i).getProject());
            }
            //
            // session.flush();
            // session.getTransaction().commit();
            System.out.println("Project Name Count : " + projectList.size());
            return projectList;

        } catch (Exception e) {


        }
        return null;
    }

    public String getRole(String developerId, String projectId) {
        try {
            System.out.println("getRole : " + developerId + " " + projectId);
            session.getTransaction().begin();
            String hql = "from Assignment where developerId= ? and project.projectId = ?";

            // String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
            Query query = session.createQuery(hql);
            query.setString(0, developerId);
            query.setString(1, projectId);
            Assignment assi = (Assignment) query.uniqueResult();
            if (assi.getType() == 1) {
                return "Project Manager";
            } else if (assi.getType() == 2) {
                return "Developer";
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            session.close();

        }
        return null;
    }

    public Timesheet getTimesheetById(BigDecimal id) {
        try {
            session.getTransaction().begin();
            String hql = "from Timesheet where timesheetId =:prId";
            Query query = session.createQuery(hql);
            query.setParameter("prId", id);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Timesheet timesheet = (Timesheet) query.uniqueResult();
            timesheet.setOccurDateString(sdf.format(timesheet.getOccurDate()));
            return timesheet;
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return null;
        }
    }

    public List<Timesheet> getTimesheetList(String developerId, String projectId, String fromDateString,
            String toDateString, String status) {
        try {
            session.getTransaction().begin();
            String hql = "from Timesheet where developerId= :devId ";
            boolean projFlag = false;
            boolean fromDFlag = false;
            boolean toDFlag = false;
            boolean statusFlag = false;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

            if (!"All".equals(projectId)) {
                hql += "and project.projectId= :projId ";
                projFlag = true;
            }
            if (fromDateString != null && !fromDateString.isEmpty()) {

                hql += "and occurDate >= :fromDate ";
                fromDFlag = true;
            }
            if (toDateString != null && !toDateString.isEmpty()) {

                hql += "and occurDate <= :toDate ";
                toDFlag = true;
            }
            if (!"All".equals(status)) {
                hql += "and status = :status";
                statusFlag = true;
            }
            hql += " order by occurDate desc";
            BigDecimal devId = new BigDecimal(developerId);
            System.out.println("HQL" + hql);
            Query query = session.createQuery(hql);
            query.setParameter("devId", (devId));
            if (projFlag) {
                query.setParameter("projId", new BigDecimal(projectId));
            }
            if (fromDFlag) {
                Date fromDate = df.parse(fromDateString);
                query.setParameter("fromDate", fromDate);
            }
            if (toDFlag) {
                Date toDate = df.parse(toDateString);
                query.setParameter("toDate", toDate);
            }
            if (statusFlag) {
                query.setParameter("status", new BigDecimal(status));
            }
            System.out.println("query timesheet : " + hql);

            List<Timesheet> timesheetList = query.list();
            for (int i = 0; i < timesheetList.size(); i++) {
                timesheetList.get(i).setOccurDateString(df.format(timesheetList.get(i).getOccurDate()));
            }

            // session.flush();
            // session.getTransaction().commit();
            System.out.println("Timesheet Record : " + timesheetList.size());
            return timesheetList;

        } catch (Exception e) {

        }
        return null;
    }

    public List<openones.oopms.entity.Process> getProcessList() {
        // session.getTransaction().begin();
        String hql = "from Process order by processId";
        Query query = session.createQuery(hql);
        List<openones.oopms.entity.Process> processList = query.list();

        // session.flush();
        // session.getTransaction().commit();
        System.out.println("Process Record : " + processList.size());
        return processList;
    }

    public List<Typeofwork> getTOWList() {
        // session.getTransaction().begin();
        String hql = "from Typeofwork order by towId";
        Query query = session.createQuery(hql);
        List<Typeofwork> towList = query.list();

        // session.flush();
        // session.getTransaction().commit();
        System.out.println("towList Record : " + towList.size());
        return towList;
    }

    public List<Workproduct> getWorkProductList() {
        // session.getTransaction().begin();
        String hql = "from Workproduct order by wpId";
        Query query = session.createQuery(hql);
        List<Workproduct> workProductList = query.list();

        session.flush();
        session.getTransaction().commit();
        System.out.println("workProductList Record : " + workProductList.size());
        return workProductList;
    }

    public Project getProject(String projectId) {
        try {
            String hql = "from Project where projectId =:projId";
            Query query = session.createQuery(hql);
            query.setParameter("projId", new BigDecimal(projectId));
            Project project = (Project) query.uniqueResult();
            return project;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return null;
        }

    }

    public void insertTimesheet(List<Timesheet> timesheetList, BigDecimal devId) throws ParseException {

        try {
            Project project;
            session.getTransaction().begin();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            for (int i = 0; i < timesheetList.size(); i++) {
                
                project = getProject(timesheetList.get(i).getProjectName());

                timesheetList.get(i).setProject(project);
                timesheetList.get(i).setDeveloperId(devId);
                timesheetList.get(i).setWpId(new BigDecimal(1));
                timesheetList.get(i).setKpaId(new BigDecimal(1));
                timesheetList.get(i).setCreateDate(new Date());
                timesheetList.get(i).setStatus(new BigDecimal(0));
                timesheetList.get(i).setProcessId(new BigDecimal(timesheetList.get(i).getProcessName()));
                timesheetList.get(i).setTowId(new BigDecimal(timesheetList.get(i).getTowName()));
                timesheetList.get(i).setOccurDate(sdf.parse(timesheetList.get(i).getOccurDateString()));
                timesheetList.get(i).setDuration(new BigDecimal(timesheetList.get(i).getDurationString()));
                session.save(timesheetList.get(i));
                
            }

            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            // session.getTransaction().rollback();
            // session.close();

        }
    }

    public void updateTimesheet(List<Timesheet> timesheetList, BigDecimal devId) throws ParseException {

        try {

        
            String hql = "UPDATE Timesheet ts SET ts.project =:project, ts.developerId =:developerId,"
                    + "ts.wpId =:wpId, ts.kpaId=:kpaId, ts.towId= :towId, ts.createDate =:createDate, ts.status =:status,"
                    + "ts.processId =:processId, ts.duration =:duration, ts.occurDate =:occurDate WHERE ts.timesheetId =:tsId";

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            for (int i = 0; i < timesheetList.size(); i++) {
                session.getTransaction().begin();
                Query query = session.createQuery(hql);
                query.setParameter("project", getProject(timesheetList.get(i).getProjectName()));
                query.setParameter("wpId", new BigDecimal(1));
                query.setParameter("kpaId", new BigDecimal(1));
                query.setParameter("towId", timesheetList.get(i).getTowId());
                query.setParameter("processId", timesheetList.get(i).getProcessId());
                query.setParameter("duration", timesheetList.get(i).getDuration());
                query.setParameter("occurDate", sdf.parse(timesheetList.get(i).getOccurDateString()));
                query.setParameter("createDate", new Date());
                query.setParameter("tsId", timesheetList.get(i).getTimesheetId());
                query.setParameter("status", new BigDecimal(0));
                query.setParameter("developerId", devId);

                int rowCount = query.executeUpdate();
                session.flush();
                session.getTransaction().commit();
                System.out.println("Rows affected: " + rowCount);
            }
            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            // session.getTransaction().rollback();
            // session.close();

        }
    }

    public void approveRejectTimesheet(List<Timesheet> timesheetList, BigDecimal devId, boolean approveFlag)
            throws ParseException {

        try {

            session.getTransaction().begin();
            String hql = "UPDATE Timesheet ts SET  ts.status =:status";
            if (!approveFlag) {
                hql += ", ts.rcomment =:rcomment";
            }
            hql += " WHERE ts.timesheetId =:tsId";

            for (int i = 0; i < timesheetList.size(); i++) {
                Query query = session.createQuery(hql);

                query.setParameter("tsId", timesheetList.get(i).getTimesheetId());

                if (approveFlag) {
                    query.setParameter("status", new BigDecimal(1));
                } else {
                    query.setParameter("rcomment", timesheetList.get(i).getRcomment());
                    query.setParameter("status", new BigDecimal(2));
                }

                int rowCount = query.executeUpdate();

                System.out.println("Rows affected: " + rowCount);
            }
            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            // session.getTransaction().rollback();
            // session.close();

        }
    }

    public void deleteTimesheet(List<Timesheet> timesheetList, BigDecimal devId) throws ParseException {

        try {

            session.getTransaction().begin();
            String hql = "DELETE Timesheet WHERE timesheetId =:tsId";

            for (int i = 0; i < timesheetList.size(); i++) {
                Query query = session.createQuery(hql);
                query.setParameter("tsId", timesheetList.get(i).getTimesheetId());

                int rowCount = query.executeUpdate();

                System.out.println("Rows affected: " + rowCount);
            }
            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            // session.getTransaction().rollback();
            // session.close();

        }
    }
}
