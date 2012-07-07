package openones.oopms.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.model.Tasks;
import openones.oopms.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TaskDAO {
    private Session session;
    
    public TaskDAO() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    
    public void save (Tasks task){
        try {
        session.getTransaction().begin();
        session.save(task);
        session.flush();
        session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public Tasks authenticate (String username, String password){
        try {
            session.getTransaction().begin();
            String sql = "from Tasks where username = ? and password = ?";
            Query query = session.createQuery(sql);
            query.setString(0, username);
            query.setString(1, password);

            Tasks result = (Tasks)query.uniqueResult();
            session.flush();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public Tasks[] findByLastName (String lastname){
        try {
            session.getTransaction().begin();
            String sql = "from Tasks where lastname like ?";
            Query query = session.createQuery(sql);
            query.setString(0, "%" + lastname + "%");
            List resultList = query.list();

            Tasks[] taskArray = new Tasks[resultList.size()];
            resultList.toArray(taskArray);
            session.flush();
            session.getTransaction().commit();
            return taskArray;
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public void update (Tasks newTask, BigDecimal id){
        try {
            session.getTransaction().begin();
            
            Tasks task = (Tasks)session.get(Tasks.class, id);
            task.setTaskid(newTask.getTaskid());
            task.setWorkunit(newTask.getWorkunit());
            task.setDescription(newTask.getDescription());
            task.setAssignedto(newTask.getAssignedto());
            task.setEffort(newTask.getEffort());
            task.setPlanDate(newTask.getPlanDate());
            task.setActualDate(newTask.getActualDate());
            task.setStatus(newTask.isStatus());
            task.setType(newTask.getType());
            task.setNote(newTask.getNote());
            task.setProcess(newTask.getProcess());
            task.setReplanDate(newTask.getReplanDate());
            task.setFeasible(newTask.getFeasible());
            task.setCode(newTask.getCode());
            
            session.update(task);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete (int id){
        try {
            session.getTransaction().begin();
            session.delete(session.get(Tasks.class, id));
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}
