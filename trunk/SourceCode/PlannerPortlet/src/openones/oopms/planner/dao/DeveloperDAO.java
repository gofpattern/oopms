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

import openones.oopms.planner.model.Developer;
import openones.oopms.planner.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 */
public class DeveloperDAO {
    private Session session;
    private static Logger log = Logger.getLogger(TaskDAO.class);

    public DeveloperDAO() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
    }

    public BigDecimal getDeveloperId(String account) {
        log.debug("getDeveloperId.START");
        try {
            session.getTransaction().begin();
            String sql = "select developerId from Developer where UPPER(account) = :account";
            Query query = session.createQuery(sql);
            query.setParameter("account", account.toUpperCase());
            BigDecimal developerId = (BigDecimal) query.uniqueResult();
            return developerId;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getDeveloperId.Exception", e);
        }
        return null;
    }

    public Developer getDeveloperByAccount(String account) {
        log.debug("getDeveloperByAccount.START");
        try {

            session.getTransaction().begin();
            String sql = "from Developer where UPPER(account) = :account";
            Query query = session.createQuery(sql);
            query.setParameter("account", account.toUpperCase());
            Developer developer = (Developer) query.uniqueResult();
            log.debug("developer="+developer.getName());
            return developer;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getDeveloperByAccount.Exception", e);
        }
        return null;
    }
}
