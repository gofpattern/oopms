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

import java.util.List;

import openones.oopms.entity.BusinessDomain;
import openones.oopms.entity.GeneralReference;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 *
 */
public class BusinessDomainDao {
    private Session session;
    private static Logger log = Logger.getLogger(BusinessDomainDao.class);

    public BusinessDomainDao() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
    }

    public List<BusinessDomain> getBusinessDomain() {
        log.debug("getBusinessDomain.START");
        try {
            session.getTransaction().begin();
            String sql = "from BusinessDomain";
            Query query = session.createQuery(sql);
            @SuppressWarnings("unchecked")
            List<BusinessDomain> domainList = query.list();

            return domainList;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getBusinessDomain.ERROR", e);
        }
        return null;
    }
   
}
