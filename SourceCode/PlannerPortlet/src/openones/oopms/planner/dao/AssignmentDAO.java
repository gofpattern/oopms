/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package openones.oopms.planner.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.utils.Constant;
import openones.oopms.planner.utils.HibernateUtil;
import openones.oopms.planner.model.Assignment;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 */
public class AssignmentDAO {
    private Session session;
    private static Logger log = Logger.getLogger(AssignmentDAO.class);

    public AssignmentDAO() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
    }

    /**
     * Get List of project correspond to an user.
     * @param developerId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Project> getProject(BigDecimal developerId) {
        log.debug("getProject.START");
        try {
            session.getTransaction().begin();
            String sql = "select project from Assignment ass where ass.developer.developerId = :developerId "
                    + "and ((ass.endDate > :currentDate) or (ass.endDate is null))";
            Query query = session.createQuery(sql);
            query.setParameter("developerId", developerId);
            query.setParameter("currentDate", new Date());
            List<Project> projectList = query.list();
            return projectList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getProject.Exception", e);
        }
        return null;
    }
    
    /**
     * Get List of project correspond to an user.
     * @param developerId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Developer> getDeveloperByProject(BigDecimal projectId) {
        log.debug("getDeveloperByProject.START");
        try {
            session.getTransaction().begin();
            String sql = "select developer from Assignment ass where ass.project.projectId = :projectId "
                    + "and ((ass.endDate > :currentDate) or (ass.endDate is null))";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", projectId);
            query.setParameter("currentDate", new Date());
            List<Developer> developers = query.list();
            return developers;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getDeveloperByProject.Exception", e);
        }
        return null;
    }

    /**
     * Get role of an user in a project.
     * @param developerId
     * @param projectId
     * @return
     */
    public String getRole(String developerId, String projectId) {
        try {
            log.debug("getRole.START");
            log.debug("getRole.START"+developerId);
            log.debug("getRole.START"+projectId);
            session.getTransaction().begin();
            String hql = "from Assignment where developer.developerId= ? and project.projectId = ?"
                    + " and ((endDate > :currentDate) or (endDate is null))";
            Query query = session.createQuery(hql);
            query.setString(0, developerId);
            query.setString(1, projectId);
            query.setParameter("currentDate", new Date());
            Assignment assi = (Assignment) query.uniqueResult();
            if (assi.getType() == 1 || assi.getType() == 0) {
                log.debug("getRole.END Role is Project Manager");
                return Constant.PROJECT_MANAGER;
            } else if (assi.getType() == 2) {
                log.debug("getRole.END Role is Developer");
                return Constant.DEVELOPER;
            } else if (assi.getType() == 3) {
                log.debug("getRole.END Role is Tester");
                return Constant.TESTER;
            } else if (assi.getType() == 4) {
                log.debug("getRole.END Role is QA");
                return Constant.QA;
            } else if (assi.getType() == 5) {
                log.debug("getRole.END Role is Customer");
                return Constant.CUSTOMER;
            } else if (assi.getType() == 6) {
                log.debug("getRole.END Role is Project Owner");
                return Constant.PROJECT_OWNER;
            }

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getRole.Exception", e);
        }
        return null;
    }
}
