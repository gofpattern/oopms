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

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Open-Ones team
 */
public class BaseDao {
    /** Logging. */
    public final Logger log = Logger.getLogger(this.getClass());

    /** . */
    Session sess = null;

    /**
     * Get next sequence in Oracle.
     * @param seqName
     * @return
     */
    public final BigDecimal getNextSeq(String seqName) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        sess = sessionfactory.openSession();
        sess.beginTransaction();
        String sql = "SELECT DEVELOPER_SEQ.NEXTVAL as nextValue FROM dual";
        Query query = sess.createSQLQuery(sql).addScalar("nextValue", Hibernate.BIG_DECIMAL);
        BigDecimal nextId = (BigDecimal) query.list().get(0);

        return nextId;
    }
}
