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
import java.util.Hashtable;
import java.util.List;
import openones.oopms.daocommon.HibernateUtil;
import openones.oopms.entity.Assignment;
import openones.oopms.entity.Developer;
import openones.oopms.entity.Project;
import openones.oopms.entity.ex.Right;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

/**
 * @author Open-Ones team
 */
public class BaseDao {
    /** Logging. */
    public final Logger log = Logger.getLogger(this.getClass());

    /** . */
    Session session = null;

    /**
     * Get next sequence in Oracle.
     * @param seqName
     * @return
     */
    public final BigDecimal getNextSeq(String seqName) {
        try {
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            session = sessionfactory.openSession();
            session.beginTransaction();
            String sql = "SELECT DEVELOPER_SEQ.NEXTVAL as nextValue FROM dual";
            Query query = session.createSQLQuery(sql).addScalar("nextValue", Hibernate.BIG_DECIMAL);
            BigDecimal nextId = (BigDecimal) query.list().get(0);

            return nextId;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
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
            session.getTransaction().rollback();
            session.close();

        }
        return null;
    }

    public List<Developer> getMemberList(String projectId) {
        try {

            session.getTransaction().begin();
            String hql = "from Assignment where project.projectId=?";

            // String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
            Query query = session.createQuery(hql);
            query.setString(0, projectId);
            List<Assignment> assiList = query.list();
            List<BigDecimal> memberListBD = new ArrayList<BigDecimal>();
            for (int i = 0; i < assiList.size(); i++) {
                memberListBD.add(assiList.get(i).getDeveloperId());
            }
            List<Developer> devList = new ArrayList<Developer>();
            DeveloperDao devDao = new DeveloperDao();
            for (int i = 0; i < memberListBD.size(); i++) {
                devList.add(devDao.getDeveloperById(memberListBD.get(i)));
            }

            return devList;

        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();

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

    /**
     * Get roles of user with given id.
     * Query template
     * SELECT A.RIGHTGROUPID, A.WORKUNITID, B.TYPE, B.WORKUNITNAME,B.TABLEID 
     *   FROM RIGHTGROUPOFUSERBYWORKUNIT A, WORKUNIT B 
     *   WHERE DEVELOPERID = ? AND B.WORKUNITID = A.WORKUNITID ORDER BY B.WORKUNITNAME
     * 
     * Explanation:
     * WORKUNITNAME: Code of Project if TYPE = 2
     * RIGHTGROUPID: Role of give account id for WorkUnit
     *   PD: Project Manager
     *   PL: Project Leader
     *   Tester: Tester
     *   TeamMember: Developer
     * 
     * @param developerId Identifier of user (For sysadmin, id = 1)
     * @return List of instances of RoleRight
     * @author Open-Ones team
     * @see http://open-ones.googlecode.com/svn/trunk/ProjectList/iPMS/SourceCode/FsoftInsight/src/com/fms1/common/Roles.java
     * , method Vector getRightOfUser(final long developerID)
     */
    public List<Right> getRights(BigDecimal developerId) {
        try {
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            session = sessionfactory.openSession();
            session.beginTransaction();
            String sql = "SELECT A.RIGHTGROUPID, A.WORKUNITID, B.TYPE, B.WORKUNITNAME,B.TABLEID"
                    + " FROM RIGHTGROUPOFUSERBYWORKUNIT A, WORKUNIT B WHERE DEVELOPERID = ?"
                    + " AND B.WORKUNITID = A.WORKUNITID ORDER BY B.WORKUNITNAME";

            Query query = session.createSQLQuery(sql)
                    .addScalar("RIGHTGROUPID", Hibernate.STRING)
                    .addScalar("WORKUNITID", Hibernate.BIG_DECIMAL)
                    .addScalar("TYPE", Hibernate.BIG_DECIMAL)
                    .addScalar("WORKUNITNAME", Hibernate.STRING)
                    .addScalar("TABLEID", Hibernate.BIG_DECIMAL)
                    .setResultTransformer(Transformers.aliasToBean(Right.class));
            query.setBigDecimal(0, developerId);
            List<Right> roleList = query.list();

            Right roleRight;
            for (int i = 0; i < roleList.size(); i++) {
                roleRight = roleList.get(i);
                roleRight.setRIGHTGROUPID(roleRight.getRIGHTGROUPID().trim());
                roleList.set(i, roleRight);
                // System.out.println("'" + roleList.get(i).getRIGHTGROUPID().trim() + "'");
            }

            return roleList;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
    
    /**
     * Build Hashtable from list of right.
     * @param rightList
     * @return Hashtable<WordUnit ID, Right>
     */
    public Hashtable<BigDecimal, String> buildWUTable(List<Right> rightList) {
        Hashtable<BigDecimal, String> wuTable = new Hashtable<BigDecimal, String>();
        int len = (rightList != null) ? rightList.size(): 0;
        for (int i = 0; i < len; i++) {
            wuTable.put(rightList.get(i).getWORKUNITID(), rightList.get(i).getRIGHTGROUPID());
        }
        
        return wuTable;
    }
}
