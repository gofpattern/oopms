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

import openones.oopms.entity.DefectType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * @author OOG Member
 */
public class DefectTypeDao {
    /** . */
    private Session sess = null;

    /** . */
    private Transaction tx = null;

    /**
     * [Give the description for method].
     * @return a
     */
    public final ArrayList<DefectType> getDefectType() {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        tx = sess.beginTransaction();

        List list = sess.createQuery("from openones.oopms.entity.DefectType").list();
        ArrayList<DefectType> dtList = new ArrayList<DefectType>();
        for (int i = 0; i < list.size(); ++i) {
            dtList.add((DefectType) list.get(i));
        }
        return dtList;
        // return (QcActivity) sess.get(QcActivity.class, code);
    }
}
