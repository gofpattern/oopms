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
package openones.oopms.requirement.dao;

import java.math.BigDecimal;
import java.util.Date;

import openones.oopms.requirement.model.Developer;
import openones.oopms.requirement.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Kenda
 */
public class DeveloperDao {
    private Session session;
    private static Logger log = Logger.getLogger(DeveloperDao.class);

    public DeveloperDao() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }

    public final boolean insertDeveloper(Developer dev) {
        log.debug("InsertDeveloperId.START");
        try {
            SessionFactory sessFact = HibernateUtil.getSessionFactory();
            session = sessFact.openSession();
            Transaction tx = session.beginTransaction();

            // Get max id
            // Query query = sess.createQuery(SQL_MAX_DEV_ID);
            String sql = "SELECT DEVELOPER_SEQ.NEXTVAL as nextValue FROM dual";
            Query query = session.createSQLQuery(sql).addScalar("nextValue", Hibernate.BIG_DECIMAL);
            BigDecimal nextId = (BigDecimal) query.list().get(0);

            // Update next id
            dev.setDeveloperId(nextId);
            // Uppercase Account
            dev.setAccount(dev.getAccount().toUpperCase());

            // Set BeginDate as default if null
            if (dev.getBeginDate() == null) {
                dev.setBeginDate(new Date());
            }

            session.save(dev);
            tx.commit();
            sessFact.close();
            return true;
        } catch (RuntimeException rEx) {
            log.error("Saving Developer...", rEx);
            return false;
        } finally {
            session.close();
        }
    }

    public BigDecimal getDeveloperId(String account) {
        log.debug("getDeveloperId.START");
        try {
            SessionFactory sessfac = HibernateUtil.getSessionFactory();
            session = sessfac.openSession();
            session.getTransaction().begin();
            String sql = "select developerId from Developer where account = :account";
            Query query = session.createQuery(sql);
            query.setParameter("account", account);
            BigDecimal developerId = (BigDecimal) query.uniqueResult();
            session.flush();
            System.out.println("getDeveloperId.end");
            return developerId;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getDeveloperId.Exception", e);
        } finally {
            session.close();
        }
        return null;
    }

    public Developer getDeveloperByAccount(String account) {
        log.debug("getDeveloperByAccount.START");
        try {
            SessionFactory sessfac = HibernateUtil.getSessionFactory();
            session = sessfac.openSession();
            session.getTransaction().begin();
            String sql = "from Developer where account = :account";
            Query query = session.createQuery(sql);
            query.setParameter("account", account);
            Developer developer = (Developer) query.uniqueResult();
            // session.flush();
            System.out.println("getDeveloperByAccount.end");
            return developer;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getDeveloperByAccount.Exception", e);
        } finally {
            session.close();
        }
        return null;
    }
}
