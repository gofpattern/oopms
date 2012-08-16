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
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 *
 */
public class CostDao {
    private Session session;
    private static Logger log = Logger.getLogger(CostDao.class);

    public CostDao() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
    }
    public String getCostStatus(BigDecimal projectId) {
        log.debug("getCostStatus.START");
        try {
            session.getTransaction().begin();
            String sql = "select costStatus from OopmsProjectCost where projectId = :projectId";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", projectId);
            String costStatus = (String)query.uniqueResult();
            return costStatus;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getCostStatus.ERROR", e);
        }
        return null;
    }
}
