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
package openones.oopms.dms.util;

import static org.junit.Assert.*;

import java.util.List;

import openones.oopms.dms.form.MenuItem;
import openones.oopms.dms.form.SubMenu;

import org.junit.Test;

/**
 * @author thachle
 *
 */
public class AppUtilTest {

    /**
     * Test method for {@link openones.oopms.dms.util.AppUtil#loadMenuBar()}.
     */
    @Test
    public void testLoadMenuBar() {
        List<SubMenu> subMenuList = AppUtil.loadMenuBar();
        
        assertEquals(2, subMenuList.size());
        
        assertEquals("Project Environment", subMenuList.get(0).getName());
        assertEquals("Manage Defect", subMenuList.get(1).getName());
        
        List<MenuItem> menuItemList = subMenuList.get(0).getMenuItemList();
        assertEquals(4, menuItemList.size());
        assertEquals("Manage Views", subMenuList.get(1).getMenuItemList().get(0).getName());
        assertEquals("goManageViews", subMenuList.get(1).getMenuItemList().get(0).getActionId());
    }

}