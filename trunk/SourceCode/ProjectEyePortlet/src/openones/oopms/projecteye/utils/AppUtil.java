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
package openones.oopms.projecteye.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import openones.oopms.projecteye.form.MenuItem;
import openones.oopms.projecteye.form.SubMenu;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rocky.common.CommonUtil;
import rocky.common.XMLUtil;

/**
 * @author Thach.Le
 */
public class AppUtil {
    /** Logger for logging. */
    private final static Logger log = Logger.getLogger("AppUtil");

    /** . */
    final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    /**
     * [Give the description for method].
     * @return
     */
    public static String getCurrentDate() {
        return sdf.format(new Date());
    }

    public static List<SubMenu> loadMenuBar() {
        List<SubMenu> subMenuList = new ArrayList<SubMenu>();
        try {

            Document menuBarDoc = XMLUtil.parse(CommonUtil.loadResource("/MenuBar.xml"));
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            // Nodes of rule
            NodeList subMenuNodeList = (NodeList) xp.evaluate("//SubMenu", menuBarDoc, XPathConstants.NODESET);

            int len = (subMenuNodeList != null) ? subMenuNodeList.getLength() : 0;

            Node subMenuNode;
            SubMenu subMenu;
            for (int i = 0; i < len; i++) {
                subMenu = new SubMenu();
                subMenuNode = subMenuNodeList.item(i);
                subMenu.setName(xp.evaluate("@name", subMenuNode));
                subMenu.setActionId(xp.evaluate("@action", subMenuNode));
                subMenu.addMenuItem(parseMenuItem(subMenuNode));

                subMenuList.add(subMenu);
            }

        } catch (Exception ex) {
            log.error("Loading resource /MenuBar.xml", ex);
        }

        return subMenuList;
    }

    private static List<MenuItem> parseMenuItem(Node subMenuNode) throws XPathExpressionException {
        List<MenuItem> menuItemList = new ArrayList<MenuItem>();
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xp = xpf.newXPath();

        NodeList menuItemNodeList = (NodeList) xp.evaluate("Item", subMenuNode, XPathConstants.NODESET);

        int len = (menuItemNodeList != null) ? menuItemNodeList.getLength() : 0;

        MenuItem menuItem;
        Node menuItemNode;
        for (int i = 0; i < len; i++) {
            menuItem = new MenuItem();
            menuItemNode = menuItemNodeList.item(i);
            menuItem.setName(xp.evaluate("@name", menuItemNode));
            menuItem.setActionId(xp.evaluate("@action", menuItemNode));

            menuItemList.add(menuItem);
        }

        return menuItemList;
    }
    
    public static String getDateAsFormat(Date date, String formatString) {
    	String result = "";
    	DateFormat df = new java.text.SimpleDateFormat(formatString);
   		result = df.format(date);
    	return result;
    }
    
    public static Date getDateFromFormattedDate(String formattedDate, String formatString) {
    	DateFormat formatter = new SimpleDateFormat(formatString);
		Date result;
		try {
			result = (Date) formatter.parse(formattedDate);
		} catch (ParseException e) {
			log.error(e.getMessage());
			return null;
		}
    	return result;
    }
}
