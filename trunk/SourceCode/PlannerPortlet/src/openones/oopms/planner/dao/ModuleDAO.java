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
import java.util.List;

import openones.oopms.planner.model.Module;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.model.Workproduct;
import openones.oopms.planner.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author PNTG
 */
public class ModuleDAO {
    private Session session;
    private static Logger log = Logger.getLogger(TaskDAO.class);

    public ModuleDAO() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.openSession();

    }

    public List<Module> getModuleByProject(BigDecimal projectId) {
        log.debug("getModuleByProject.START");
        try {
            session.beginTransaction();
            String sql = "from Module where project.projectId = :projectId";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", projectId);
            @SuppressWarnings("unchecked")
            List<Module> moduleList = query.list();
            return moduleList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getModuleByProject.Exception", e);
        }
        return null;
    }

    public List<Workproduct> getWorkproductByProject(BigDecimal projectId) {
        log.debug("getWorkproductByProject.START");
        try {
            session.beginTransaction();
            String sql = "select workproduct from Module md where md.project.projectId = :projectId";
            Query query = session.createQuery(sql);
            query.setParameter("projectId", projectId);
            @SuppressWarnings("unchecked")
            List<Workproduct> wproductList = query.list();
            return wproductList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getWorkproductByProject.Exception", e);
        }
        return null;
    }

    public List<BigDecimal> getTasksStatusByModule(BigDecimal moduleId) {
        log.debug("getTasksStatusByModule.START");
        try {
            session.beginTransaction();
            String sql = "select statusid from Tasks t where t.module.moduleId = :moduleId "
                    + "and active = true and statusid != 175";
            Query query = session.createQuery(sql);
            query.setParameter("moduleId", moduleId);
            @SuppressWarnings("unchecked")
            List<BigDecimal> taskStatusList = query.list();
            return taskStatusList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getTasksStatusByModule.Exception", e);
        }
        return null;
    }

    public List<Tasks> getTaskByModule(BigDecimal moduleId) {
        log.debug("getTaskByModule.START");
        try {
            session.beginTransaction();
            String sql = "from Tasks t where t.module.moduleId = :moduleId and active = true";
            Query query = session.createQuery(sql);
            query.setParameter("moduleId", moduleId);
            @SuppressWarnings("unchecked")
            List<Tasks> taskList = query.list();
            return taskList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("getTaskByModule.Exception", e);
        }
        return null;
    }

    public void updateModuleByTask(Tasks task) {
        log.debug("updateModuleByTask.START");
        try {

            session.getTransaction().begin();

            Module module = (Module) session.get(Module.class, task.getModule().getModuleId());

            module.setPlannedSize(module.getPlannedSize().add(task.getProductsize()));
            module.setActualSize(module.getActualSize().add(task.getCompletedsize()));

            module.setPlannedSizeUnitId(task.getSizeunit());
            module.setActualSizeUnitId(task.getSizeunit());
            List<BigDecimal> taskStatusList = getTasksStatusByModule(module.getModuleId());
            // for (int i = 0; i < taskStatusList.size(); i++) {
            // if (taskStatusList.get(i).equals(new BigDecimal(175))) {
            // taskStatusList.remove(taskStatusList.get(i));
            // }
            // }
            if (taskStatusList.size() == 0)
                module.setStatus(new BigDecimal(175));// 175: CANCEL
            else {
                Boolean flag = true;
                for (int i = 0; i < taskStatusList.size(); i++) {
                    if (taskStatusList.get(0) != taskStatusList.get(i))
                        flag = false;
                }
                if (flag.equals(true))
                    module.setStatus(taskStatusList.get(0));
                else
                    module.setStatus(new BigDecimal(173));// 173: ON-GOING

            }

            session.merge(module);

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("updateModuleByTask.Exception", e);
        }
    }

    public void updateModuleByEditedTask(Tasks task, Tasks editedTask) {
        log.debug("updateModuleByEditedTask.START");
        try {

            session.getTransaction().begin();

            Module module = (Module) session.get(Module.class, editedTask.getModule().getModuleId());

            if (!task.getProductsize().equals(editedTask.getProductsize())) {
                module.setPlannedSize(module.getPlannedSize().add(
                        editedTask.getProductsize().subtract(task.getProductsize())));
            }
            if (!task.getCompletedsize().equals(editedTask.getCompletedsize())) {
                module.setActualSize(module.getActualSize().add(
                        editedTask.getCompletedsize().subtract(task.getCompletedsize())));
            }

            module.setPlannedSizeUnitId(editedTask.getSizeunit());
            module.setActualSizeUnitId(editedTask.getSizeunit());
            if (!task.getStatusid().equals(editedTask.getStatusid())) {
                List<BigDecimal> taskStatusList = getTasksStatusByModule(module.getModuleId());
                // for (int i = 0; i < taskList.size(); i++) {
                // if (taskList.get(i).getStatusid().equals(new BigDecimal(175))) {
                // taskList.remove(taskList.get(i));
                // }
                // }
                if (taskStatusList.size() == 0)
                    module.setStatus(new BigDecimal(175));// 175: CANCEL
                else {
                    Boolean flag = true;
                    for (int i = 0; i < taskStatusList.size(); i++) {
                        if (taskStatusList.get(0) != taskStatusList.get(i))
                            flag = false;
                    }
                    if (flag.equals(true))
                        module.setStatus(taskStatusList.get(0));
                    else
                        module.setStatus(new BigDecimal(173));// 173: ON-GOING
                }

            }

            session.merge(module);

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("updateModuleByEditedTask.Exception", e);
        }
    }

}
