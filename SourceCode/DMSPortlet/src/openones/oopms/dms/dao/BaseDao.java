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
package openones.oopms.dms.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import openones.oopms.dms.dao.DeveloperDao;
import openones.oopms.daocommon.HibernateUtil;
import openones.oopms.entity.Assignment;
import openones.oopms.entity.Developer;
import openones.oopms.entity.Project;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Open-Ones team
 */
public class BaseDao {
    /** Logging. */
    public final Logger log = Logger.getLogger(this.getClass());

    /** . */
    Session session = null;
  public BaseDao() {
        
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    /**
     * Get next sequence in Oracle.
     * @param seqName
     * @return
     */
    public final BigDecimal getNextSeq(String seqName) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        session = sessionfactory.openSession();
        session.beginTransaction();
        String sql = "SELECT DEVELOPER_SEQ.NEXTVAL as nextValue FROM dual";
        Query query = session.createSQLQuery(sql).addScalar("nextValue", Hibernate.BIG_DECIMAL);
        BigDecimal nextId = (BigDecimal) query.list().get(0);

        return nextId;
    }

    public List<Project> getProjectList(String developerId) {
        try {
            log.debug("getProjectList-developerId :" + developerId);
            session.getTransaction().begin();
            String hql = "from Assignment where developerId= ?";

            // String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
            Query query = session.createQuery(hql);
            query.setString(0, developerId);
            List<Assignment> assiList = query.list();
            System.out.println("ass count : " + assiList.size());
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
          e.printStackTrace();

        }
        return null;
    }

   
    
    public String getRole(String developerId, String projectId) {
        try {
            System.out.println("getRole : " + developerId + " " + projectId);
            session.getTransaction().begin();
            String hql = "From Assignment where developerId = :developerId and project.projectId= :projectId and ((endDate > :currentDate) or (endDate is null)) ";

            // String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
            Query query = session.createQuery(hql);  
            query.setParameter("developerId",  new BigDecimal(developerId));  
            query.setParameter("projectId", new BigDecimal(projectId));  
            query.setParameter("currentDate", new Date());  
            Assignment assi = (Assignment)query.uniqueResult();
            if (assi.getType() == 1 || assi.getType()==0) {
                return "Project Manager";
            }
            else if(assi.getType() == 6) {
                return "Project Owner";
            }
            else {   
                return "Developer";
            }

        } catch (Exception e) {
            e.printStackTrace();
           

        }
        return null;
    }
}
