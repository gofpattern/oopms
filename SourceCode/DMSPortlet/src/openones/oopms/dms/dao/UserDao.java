package openones.oopms.dms.dao;

import openones.oopms.entity.Developer;
import openones.oopms.dms.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDao {
    private Session session;
  
    public UserDao() {
       
      SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }
    
   public Developer authenticate(String username, String password) {
       try {
           session.getTransaction().begin();
           String hql = "from Developer  where account= ?";
          
        //   String sql = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
           Query query = session.createQuery(hql);
           query.setString(0, username);
          Developer result = (Developer) query.uniqueResult();
        //   Users result =  (Users) session.createSQLQuery(sql).addEntity(Users.class).uniqueResult();
           session.flush();
           session.getTransaction().commit();
           System.out.println("system.out.println"+result.getAccount());
           return result;
           
       } catch (Exception e) {
          
           e.printStackTrace();
       }
       return null;
   }
    
 
}
