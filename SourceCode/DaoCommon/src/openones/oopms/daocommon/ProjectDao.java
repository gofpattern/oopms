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
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import openones.oopms.entity.Project;
import openones.oopms.entity.ex.ProjectEx;
import openones.oopms.entity.ex.Right;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 * @author Open-Ones team
 */
public class ProjectDao extends BaseDao {
    private Session session;
    private static Logger log = Logger.getLogger(ProjectDao.class);

    public ProjectDao() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();

    }
    
    
    @SuppressWarnings("unchecked")
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
            log.error("getProjectById.Exception", e);
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
            return projectList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getAllProject.Exception", e);
        }
        return null;
    }
    
    /**
     * Get information of the project with given rights (of user).
     * @param rightList Null means get all projects
     * @return
     * @see http://open-ones.googlecode.com/svn/trunk/ProjectList/iPMS/SourceCode/FsoftInsight/src/com/fms1/common/Project.java,
     * method Vector getProjectsByWUs(Vector projectRoles)
     */
    public List<ProjectEx> getProjectEx(List<Right> rightList) {
        String sql =
                "SELECT PROJECT.TYPE, CATEGORY, CODE, GROUP_NAME, STATUS, RANK," 
                    +  "a.WORKUNITID, a.PARENTWORKUNITID,"
                    + " LEADER, CUSTOMER, ACTUAL_FINISH_DATE, START_DATE"
                    + " FROM WORKUNIT a, PROJECT"
                    + " WHERE a.TYPE= 2 "
                    + " AND PROJECT.PROJECT_ID = a.TABLEID ORDER BY UPPER(CODE)";
        try {
            List<ProjectEx> projectList = new ArrayList<ProjectEx>();
            Query query = session.createSQLQuery(sql);
            
            Hashtable<BigDecimal, String> wuTable = buildWUTable(rightList);
            ProjectEx prjEx;
            Object[] row;
            int col;
            for (Iterator it = query.list().iterator(); it.hasNext();) {
                col = 0;
                row = (Object[]) it.next();
                prjEx = new ProjectEx();
                prjEx.setType((String) row[col++]);
                prjEx.setCategory((String) row[col++]);
                prjEx.setCode((String) row[col++]);
                prjEx.setGroupName((String) row[col++]);
                prjEx.setStatus((String) row[col++]);
                prjEx.setRank((String) row[col++]);
                prjEx.setWorkunitid((BigDecimal) row[col++]);
                prjEx.setParentworkunitid((BigDecimal) row[col++]);
                prjEx.setLeader((String) row[col++]);
                prjEx.setCustomer((String) row[col++]);
                prjEx.setActualFinishDate((Date) row[col++]);
                prjEx.setStartDate((Date) row[col++]);

                if ((rightList == null) || wuTable.containsKey(prjEx.getWorkunitid())) {
                    projectList.add(prjEx);
                }
            }
            
            return projectList;
        } catch (Exception e) {
            log.error("Get information of Projects", e);
        } finally {
            session.close();
        }
        
        return null;
    }
    
    /**
     * Get information of projects by user id .
     * @param developerId
     * @return
     * @see http://open-ones.googlecode.com/svn/trunk/ProjectList/iPMS/SourceCode/FsoftInsight/src/com/fms1/common/Project.java,
     * method Vector getProjectsByWUs(Vector projectRoles)
     */
    public List<ProjectEx> getProjects(BigDecimal developerId) {
        List<Right> rightOfUserList = getRights(developerId);
        return getProjectEx(rightOfUserList);
    }
}
