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
package openones.oopms.daocommon;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.entity.Assignment;
import openones.oopms.entity.Project;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 */
public class AssignmentDao {
    private Session session;
    private static Logger log = Logger.getLogger(AssignmentDao.class);

    public AssignmentDao() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectByDeveloperId(BigDecimal developerId) {
        log.debug("getProject.START");
        try {
            session.getTransaction().begin();
            String sql = "select project from Assignment ass where ass.developerId = :developerId";
            Query query = session.createQuery(sql);
            query.setParameter("developerId", developerId);
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

    public List<Project> getProject(BigDecimal developerId) {
        log.debug("getProject.START");
        try {
            session.getTransaction().begin();
            String sql = "select project from Assignment ass where ass.developer.developerId = :developerId";
            Query query = session.createQuery(sql);
            query.setParameter("developerId", developerId);
            List<Project> projectList = query.list();
            session.flush();
            System.out.println("getProject.end");
            return projectList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getProject.Exception", e);
        }
        return null;
    }

    public String getRole(String developerId, String projectId) {
        try {
            System.out.println("getRole : " + developerId + " " + projectId);
            session.getTransaction().begin();
            String hql = "from Assignment where developer.developerId= ? and project.projectId = ?";
            Query query = session.createQuery(hql);
            query.setString(0, developerId);
            query.setString(1, projectId);
            Assignment assi = (Assignment) query.uniqueResult();
            if (assi.getType() == 1 || assi.getType() == 0) {
                return "Project Manager";
            } else if (assi.getType() == 2) {
                return "Developer";
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
