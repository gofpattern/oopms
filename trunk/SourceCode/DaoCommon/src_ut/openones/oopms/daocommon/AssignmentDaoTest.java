package openones.oopms.daocommon;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.entity.Project;

import org.junit.Test;

public class AssignmentDaoTest {

    @Test
    public void testGetProjectByDeveloperId() {
        AssignmentDao assignmentDao  = new AssignmentDao();
        List<Project> list = assignmentDao.getProjectByDeveloperId(new BigDecimal(118224));
        assertEquals(0, list.size());
    }

}
