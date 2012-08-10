package openones.oopms.daocommon;

import java.util.ArrayList;
import java.util.List;

import openones.oopms.entity.DefectStatus;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DefectStatusDao {
    /** . */
    private Session sess = null;

    /**
     * [Give the description for method].
     * @return a
     */
    public final ArrayList<DefectStatus> getDefectStatus() {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();

        List list = sess.createQuery("from openones.oopms.entity.DefectStatus").list();
        ArrayList<DefectStatus> dpList = new ArrayList<DefectStatus>();
        for (int i = 0; i < list.size(); ++i) {
            dpList.add((DefectStatus) list.get(i));
        }
        return dpList;
        // return (QcActivity) sess.get(QcActivity.class, code);
    }

    /**
     * [Give the description for method].
     * @param id a
     * @return a
     */
    public final DefectStatus getDefectStatus(long id) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();
        return (DefectStatus) sess.get(DefectStatus.class, id);
    }
}
