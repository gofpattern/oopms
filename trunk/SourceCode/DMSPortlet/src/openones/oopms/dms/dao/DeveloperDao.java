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
import java.util.Date;
import java.util.List;

import openones.oopms.dms.util.HibernateUtil;
import openones.oopms.entity.Developer;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Open-Ones team
 */
public class DeveloperDao extends BaseDao {
    
    /** . */
    private Session sess = null;

    /** Select max value of Developer Id. */
    private static final String SQL_MAX_DEV_ID = "Select MAX(dev.developerId) FROM Developer dev";

    /**
     * Save Developer instance.
     * @param dev DeveloperId will be get next value.
     * @return true of success
     */
    public final boolean insertDeveloper(Developer dev) {
        try {
            SessionFactory sessFact = HibernateUtil.getSessionFactory();
            sess = sessFact.openSession();
            Transaction tx = sess.beginTransaction();

            // Get max id
            // Query query = sess.createQuery(SQL_MAX_DEV_ID);
            String sql = "SELECT DEVELOPER_SEQ.NEXTVAL as nextValue FROM dual";
            Query query = sess.createSQLQuery(sql).addScalar("nextValue", Hibernate.BIG_DECIMAL);
            BigDecimal nextId = (BigDecimal) query.list().get(0);

            // Update next id
            dev.setDeveloperId(nextId);
            // Uppercase Account
            dev.setAccount(dev.getAccount().toUpperCase());
            
            // Set BeginDate as default if null
            if (dev.getBeginDate() == null) {
                dev.setBeginDate(new Date());
            }

            sess.save(dev);

            tx.commit();
            sessFact.close();
            return true;
        } catch (RuntimeException rEx) {
            log.error("Saving Developer...", rEx);
            return false;
        }
    }

    /**
     * [Give the description for method].
     * @return a
     */
    public final List<Developer> getDevelopers() {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();

        List<Developer> devList = sess.createQuery("from openones.oopms.entity.Developer").list();

        return devList;
        // return (QcActivity) sess.get(QcActivity.class, code);
    }

    /**
     * Get information of Developer with given id.
     * @param id
     * @return
     */
    public final Developer getDeveloperById(long id) {
        return getDeveloperById(BigDecimal.valueOf(id));
    }

    /**
     * Get information of Developer with given id.
     * @param id
     * @return
     */
    public  Developer getDeveloperById(BigDecimal id) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();
        return (Developer) sess.get(Developer.class, id);
    }

    /**
     * Get information of Developer with given account.
     * @param account
     * @return
     */
    public final Developer getDeveloperByAccount(String account) {
        try {
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            sess = sessionfactory.openSession();
            sess.beginTransaction();

            String hql = "From Developer WHERE account = :username";
            Query query = sess.createQuery(hql);
            query.setParameter("username", account.toUpperCase());
            Developer dev = (Developer) query.uniqueResult();
            return dev;
        } catch (RuntimeException rEx) {
            log.error("Get information of account '" + account + "'", rEx);
        }

        return null;
    }

    /**
     * Get max value of DEVELOPER_ID.
     * @return
     */
    public final BigDecimal getMaxId() {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();

        Query query = sess.createQuery(SQL_MAX_DEV_ID);

        return (BigDecimal) query.list().get(0);
    }

    public final BigDecimal getNextSeqId() {
        return getNextSeq("DEVELOPER_SEQ");

    }
}
