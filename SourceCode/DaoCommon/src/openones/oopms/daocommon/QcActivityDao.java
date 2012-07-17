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

import java.util.ArrayList;
import java.util.List;

import openones.oopms.entity.QcActivity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author OOG Member
 */
public class QcActivityDao {

    /** . */
    private Session sess = null;

    /** . */
    private Transaction tx = null;

    /**
     * [Give the description for method].
     * @param code a
     * @return a
     */
    public final ArrayList<QcActivity> getQcActivity() {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        tx = sess.beginTransaction();

        List list = sess.createQuery("from openones.oopms.entity.QcActivity").list();
        ArrayList<QcActivity> qcList = new ArrayList<QcActivity>();
        for (int i = 0; i < list.size(); ++i) {
            qcList.add((QcActivity) list.get(i));
        }

        return qcList;
        // return (QcActivity) sess.get(QcActivity.class, code);
    }

    /**
     * [Give the description for method].
     * @param qcAc a
     * @return a
     */
    public boolean insertQcActivity(QcActivity qcAc) {
        SessionFactory sessfac = HibernateUtil.getSessionFactory();
        sess = sessfac.openSession();
        tx = sess.beginTransaction();
        sess.save(qcAc);
        tx.commit();
        sessfac.close();
        return true;
    }

    /**
     * [Give the description for method].
     * @param qcAc a
     * @return a
     */
    public boolean deleteQcActivity(QcActivity qcAc) {
        SessionFactory sessfac = HibernateUtil.getSessionFactory();
        sess = sessfac.openSession();
        tx = sess.beginTransaction();
        sess.delete(qcAc);
        tx.commit();
        sessfac.close();
        return true;
    }

    /**
     * [Give the description for method].
     * @param qcAc a
     * @return a
     */
    public boolean updateQcActivity(QcActivity qcAc) {
        SessionFactory sessfac = HibernateUtil.getSessionFactory();
        sess = sessfac.openSession();
        tx = sess.beginTransaction();
        sess.update(qcAc);
        tx.commit();
        sessfac.close();
        return true;
    }
}
