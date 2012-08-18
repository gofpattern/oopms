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

import openones.oopms.entity.Tasks;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author PNTG
 *
 */
public class TaskDao {
    private static Logger log = Logger.getLogger(TaskDao.class);
    
    
    public List<Tasks> getTasksByProjectId(BigDecimal projectId) {
        log.debug("getTaskByProjectId.START");
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.clear();
            String sql = "from Tasks where projectid = :projectId and active = true and statusid != 175";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", projectId);
            @SuppressWarnings("unchecked")
            List<Tasks> taskList = query.list();
            tx.commit();
            log.debug("getTaskByProjectId.END");
            log.debug(taskList.size());
            return taskList;

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.error("Couldn’t roll back transaction", rbe);
            }
            log.error("getTaskByProjectId.Exception", e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
