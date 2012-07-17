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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import openones.oopms.entity.QcActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author OOG member
 */
public class QcActivityDaoTest {

    /**
     * [Give the description for method].
     * @throws java.lang.Exception a
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception a
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception a
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception a
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link openones.tms.dms.dao.QcActivityDao#getQcActivity(String)}.
     */
    @Test
    public void testGetQcActivity() {
        QcActivityDao qcAcDao = new QcActivityDao();
        //QcActivity qcAc = new QcActivity();
        ArrayList<QcActivity> qcAc = new ArrayList<QcActivity>();
      //  qcAc = qcAcDao.getQcActivity("UNIT_TEST");
        qcAc = qcAcDao.getQcActivity();
        assertEquals("11-Integration test", qcAc.get(1).getName());
    }

    /**
     * Test method for {@link openones.tms.dms.dao.QcActivityDao#insertQcActivity(openones.tms.dms.entity.QcActivity)}.
     */
    @Test
    public void testInsertQcActivity() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link openones.tms.dms.dao.QcActivityDao#deleteQcActivity(openones.tms.dms.entity.QcActivity)}.
     */
    @Test
    public void testDeleteQcActivity() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link openones.tms.dms.dao.QcActivityDao#updateQcActivity(openones.tms.dms.entity.QcActivity)}.
     */
    @Test
    public void testUpdateQcActivity() {
        fail("Not yet implemented");
    }

}
