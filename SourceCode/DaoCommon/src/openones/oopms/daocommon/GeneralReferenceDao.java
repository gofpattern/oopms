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

import openones.oopms.entity.GeneralReference;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 */
public class GeneralReferenceDao {
    private Session session;
    private static Logger log = Logger.getLogger(AssignmentDao.class);

    public GeneralReferenceDao() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();
    }

    public List<GeneralReference> getProjectStatusEn() {
        log.debug("getProjectStatusEn.START");
        try {
            session.getTransaction().begin();
            String sql = "from GeneralReference where groupCode = 'PROJECT_STATUS' and languageCode.langCode  = 'en'";
            Query query = session.createQuery(sql);
            @SuppressWarnings("unchecked")
            List<GeneralReference> statusList = query.list();

            return statusList;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getProjectStatusEn.ERROR", e);
        }
        return null;
    }

    public List<GeneralReference> getProjectTypeEn() {
        log.debug("getProjectTypeEn.START");
        try {
            session.getTransaction().begin();
            String sql = "from GeneralReference where groupCode = 'PROJECT_TYPE' and languageCode.langCode  = 'en'";
            Query query = session.createQuery(sql);
            @SuppressWarnings("unchecked")
            List<GeneralReference> typeList = query.list();

            return typeList;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getProjectTypeEn.ERROR", e);
        }
        return null;
    }
    public List<GeneralReference> getProjectCategoryEn() {
        log.debug("getProjectCategoryEn.START");
        try {
            session.getTransaction().begin();
            String sql = "from GeneralReference where groupCode = 'PROJECT_CATEGORY' and languageCode.langCode  = 'en'";
            Query query = session.createQuery(sql);
            @SuppressWarnings("unchecked")
            List<GeneralReference> categoryList = query.list();

            return categoryList;

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            log.error("getProjectCategoryEn.ERROR", e);
        }
        return null;
    }
}
