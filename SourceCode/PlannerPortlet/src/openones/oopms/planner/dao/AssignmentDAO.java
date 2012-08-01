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
import java.util.List;

import openones.oopms.planner.model.Project;
import openones.oopms.planner.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 *
 */
public class AssignmentDAO {
    private Session session;
    private static Logger log = Logger.getLogger(TaskDAO.class);

    public AssignmentDAO() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
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
}
