package openones.oopms.timesheet.dao;

import openones.oopms.entity.Developer;
import openones.oopms.entity.Users;
import openones.oopms.timesheet.utils.HibernateUtil;

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
      
           System.out.println("system.out.println"+result.getAccount());
           return result;
           
       } catch (Exception e) {
          
           e.printStackTrace();
       }
       return null;
   }
    
   /* @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getSessionFactory().getCurrentSession().createQuery("from users").list();
    }

    public List<User> searchUsers(String bookName, String authorName) {
        return null;
    }

    public void addUser(final User book) {
          getSessionFactory().getCurrentSession().save(book);
    }

    public User getUser(String id) {
        String hql = "from users  and username=" + id;
        return (User) getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
    }

    public boolean isUniqueISBN(Long isbnNumber) {
        String hql = "from MyUser as book where book.active=1 and book.isbnNumber=" + isbnNumber;
        User book = (User)getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
        if(book == null) {
            return true;
        } else {
            return false;
        }
    }
*/
  
}
