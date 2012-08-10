package openones.oopms.daocommon;

import java.util.ArrayList;
import java.util.List;

import openones.oopms.entity.Process;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProcessDao {
    /** . */
    private Session sess = null;

    /**
     * [Give the description for method].
     * @return a
     */
    public final ArrayList<Process> getProcess() {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();

        List list = sess.createQuery("from openones.oopms.entity.Process").list();
        ArrayList<Process> dpList = new ArrayList<Process>();
        for (int i = 0; i < list.size(); ++i) {
            dpList.add((Process) list.get(i));
        }
        return dpList;
        // return (QcActivity) sess.get(QcActivity.class, code);
    }

    /**
     * [Give the description for method].
     * @param id a
     * @return a
     */
    public final Process getProcess(long id) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();
        return (Process) sess.get(Process.class, id);
    }
}
