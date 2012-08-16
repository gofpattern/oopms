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

import openones.oopms.entity.Module;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * @author PNTG
 */
public class ModuleDao {
    private Session session;
    private static Logger log = Logger.getLogger(ModuleDao.class);

    public ModuleDao() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();

    }

    public List<Module> getModuleByProject(BigDecimal projectId) {
        log.debug("getModuleByProject.START");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            this.session = factory.openSession();
            session.beginTransaction();
             String sql = "from Module where project.projectId = :projectId and status != 175";
             Query query = session.createQuery(sql);
             query.setParameter("projectId", projectId);
            @SuppressWarnings("unchecked")
             List<Module> moduleList = query.list();
//            List<Module> moduleList = session.createCriteria(Module.class)
//            .add(Restrictions.ne("status", new BigDecimal(175)))
//            .add(Restrictions.eq("project.projectId",projectId)).list();
            return moduleList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getModuleByProject.Exception", e);
        }
        return null;
    }

}
