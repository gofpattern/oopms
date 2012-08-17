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
package openones.oopms.dashboard.validator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import openones.oopms.daocommon.LanguageDao;
import openones.oopms.daocommon.ModuleDao;
import openones.oopms.dashboard.utils.Constant;
import openones.oopms.entity.Language;
import openones.oopms.entity.Module;

import org.junit.Test;

/**
 * @author PNTG
 */
public class DashboardTest {

    @SuppressWarnings("deprecation")
    @Test
    public void test() {
        // 662:LOC 650:PAGE 654:SHEET 656:TC
        List<Module> modules = new ArrayList<Module>();
        Module module;
        module = new Module();
        module.setActualSize(new BigDecimal(400));
        module.setPlannedSize(new BigDecimal(1000));
        module.setPlannedSizeUnitId(new BigDecimal(662));
        modules.add(module);

        module = new Module();
        module.setActualSize(new BigDecimal(500));
        module.setPlannedSize(new BigDecimal(1000));
        module.setPlannedSizeUnitId(new BigDecimal(656));
        modules.add(module);

        module = new Module();
        module.setActualSize(new BigDecimal(800));
        module.setPlannedSize(new BigDecimal(1000));
        module.setPlannedSizeUnitId(new BigDecimal(650));
        modules.add(module); 

        module = new Module();
        module.setActualSize(new BigDecimal(500));
        module.setPlannedSize(new BigDecimal(1000));
        module.setPlannedSizeUnitId(new BigDecimal(654));
        modules.add(module);

        module = new Module();
        module.setActualSize(new BigDecimal(300));
        module.setPlannedSize(new BigDecimal(1000));
        module.setPlannedSizeUnitId(new BigDecimal(656));
        modules.add(module);

        Double rs = calculatePercentProgress(new BigDecimal(118387));
        assertEquals(400f, rs, 0f);
    }
    private double calculatePercentProgress(BigDecimal projectId) {
        //log.debug("calculatePercentProgress.START");
        ModuleDao moduleDao = new ModuleDao();
        LanguageDao languageDao = new LanguageDao();
        List<Module> modules = moduleDao.getModuleByProject(projectId);
        double totalCurrentLoc = 0;
        double totalCurrentPage = 0;
        double totalCurrentTestCase = 0;
        double totalCurrentSheet = 0;

        double totalPlannedLoc = 0;
        double totalPlannedPage = 0;
        double totalPlannedTestCase = 0;
        double totalPlannedSheet = 0;
        for (int i = 0; i < modules.size(); i++) {
            Language language = languageDao.getLanguageById(modules.get(i).getPlannedSizeUnitId());
            if (language.getSizeUnit().toUpperCase().contains(Constant.LOC.toUpperCase())) {
                totalCurrentLoc += modules.get(i).getActualSize().doubleValue();
                totalPlannedLoc += modules.get(i).getPlannedSize().doubleValue();
            }

            if (language.getSizeUnit().toUpperCase().contains(Constant.TESTCASE.toUpperCase())) {
                totalCurrentTestCase += modules.get(i).getActualSize().doubleValue();
                totalPlannedTestCase += modules.get(i).getPlannedSize().doubleValue();
            }

            if (language.getSizeUnit().toUpperCase().contains(Constant.PAGE_WORD.toUpperCase())) {
                totalCurrentPage += modules.get(i).getActualSize().doubleValue();
                totalPlannedPage += modules.get(i).getPlannedSize().doubleValue();
            }
            if (language.getSizeUnit().toUpperCase().contains(Constant.SHEET_EXCEL.toUpperCase())) {
                totalCurrentSheet += modules.get(i).getActualSize().doubleValue();
                totalPlannedSheet += modules.get(i).getPlannedSize().doubleValue();
            }

        }

        double percentProgress = ((totalCurrentLoc * Constant.LOC_WEIGHT)
                + (totalCurrentTestCase * Constant.TESTCASE_WEIGHT) + (totalCurrentPage * Constant.PAGE_WEIGHT) + (totalCurrentSheet * Constant.PAGE_WEIGHT))
                / ((totalPlannedLoc * Constant.LOC_WEIGHT) + (totalPlannedTestCase * Constant.TESTCASE_WEIGHT)
                        + (totalPlannedPage * Constant.PAGE_WEIGHT) + (totalPlannedSheet * Constant.PAGE_WEIGHT)) * 100;

        return Math.round(percentProgress * 100.0) / 100.0;
    }
    /*private double calculatePercentProgress(List<Module> modules) {

        LanguageDao languageDao = new LanguageDao();
        double totalCurrentLoc = 0;
        double totalCurrentPage = 0;
        double totalCurrentTestCase = 0;
        double totalCurrentSheet = 0;

        double totalPlannedLoc = 0;
        double totalPlannedPage = 0;
        double totalPlannedTestCase = 0;
        double totalPlannedSheet = 0;
        for (int i = 0; i < modules.size(); i++) {
            Language language = languageDao.getLanguageById(modules.get(i).getPlannedSizeUnitId());
            if (language.getSizeUnit().toUpperCase().equals(Constant.LOC.toUpperCase())) {
                totalCurrentLoc += modules.get(i).getActualSize().doubleValue();
                totalPlannedLoc += modules.get(i).getPlannedSize().doubleValue();
            }

            if (language.getSizeUnit().toUpperCase().equals(Constant.TESTCASE.toUpperCase())) {
                totalCurrentTestCase += modules.get(i).getActualSize().doubleValue();
                totalPlannedTestCase += modules.get(i).getPlannedSize().doubleValue();
            }

            if (language.getSizeUnit().toUpperCase().equals(Constant.PAGE_WORD.toUpperCase())) {
                totalCurrentPage += modules.get(i).getActualSize().doubleValue();
                totalPlannedPage += modules.get(i).getPlannedSize().doubleValue();
            }
            if (language.getSizeUnit().toUpperCase().equals(Constant.SHEET_EXCEL.toUpperCase())) {
                totalCurrentSheet += modules.get(i).getActualSize().doubleValue();
                totalPlannedSheet += modules.get(i).getPlannedSize().doubleValue();
            }

        }

        double percentProgress = ((totalCurrentLoc * Constant.LOC_WEIGHT)
                + (totalCurrentTestCase * Constant.TESTCASE_WEIGHT) + (totalCurrentPage * Constant.PAGE_WEIGHT) + (totalCurrentSheet * Constant.PAGE_WEIGHT))
                / ((totalPlannedLoc * Constant.LOC_WEIGHT) + (totalPlannedTestCase * Constant.TESTCASE_WEIGHT)
                        + (totalPlannedPage * Constant.PAGE_WEIGHT) + (totalPlannedSheet * Constant.PAGE_WEIGHT)) * 100;

         return Math.round(percentProgress * 100.0) / 100.0;
//        return totalCurrentLoc;
    }*/
}
