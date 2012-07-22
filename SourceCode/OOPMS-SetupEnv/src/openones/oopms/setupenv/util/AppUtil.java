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
package openones.oopms.setupenv.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

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

    /**
     * Load Setup Environments.
     * @return
     */
    public static List<String> loadEnvs() {
        List<String> envList = new ArrayList<String>();
        try {

            Document envDoc = XMLUtil.parse(CommonUtil.loadResource("/SetupEnv.xml"));
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            // Nodes of rule
            NodeList dataNodeList = (NodeList) xp.evaluate("//data", envDoc, XPathConstants.NODESET);

            int len = (dataNodeList != null) ? dataNodeList.getLength() : 0;

            Node dataNode;
            for (int i = 0; i < len; i++) {
                dataNode = dataNodeList.item(i);
                envList.add(xp.evaluate("@name", dataNode));
            }

        } catch (Exception ex) {
            log.error("Loading resource /SetupEnv.xml", ex);
        }

        return envList;
    }
}
