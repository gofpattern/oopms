package openones.oopms.daocommon;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.entity.Developer;
import openones.oopms.entity.ex.Right;

import org.junit.Test;

public class BaseDaoTest {

    /**
     * Testing: Get roles of user by account name.
     */
    @Test
    public void testGetRoles() {
        String account = "sysadmin";
        
        DeveloperDao devDao = new DeveloperDao();
        Developer dev = devDao.getDeveloperByAccount(account);
        
        BigDecimal devId = dev.getDeveloperId();
        BaseDao dao = new BaseDao();
        
        List<Right> roles = dao.getRights(devId);
        
        for (int i = 0; i < roles.size(); i++) {
            System.out.println("Role " + i + ":" + roles.get(i).getRIGHTGROUPID() + ":"
                    + roles.get(i).getWORKUNITNAME());
        }
        
        assertNotNull(roles);
        assertEquals("admin", roles.get(0).getRIGHTGROUPID());
    }

    /**
     * Testing: Get roles of user by account name.
     */
    @Test
    public void testGetRoles02() {
        String account = "thachln";
        
        DeveloperDao devDao = new DeveloperDao();
        Developer dev = devDao.getDeveloperByAccount(account);
        
        BigDecimal devId = dev.getDeveloperId();
        BaseDao dao = new BaseDao();
        
        List<Right> roles = dao.getRights(devId);
        
        for (int i = 0; i < roles.size(); i++) {
            System.out.println("Role " + i + ":" + roles.get(i).getRIGHTGROUPID() + ":"
                    + roles.get(i).getWORKUNITNAME());
        }
        
        assertNotNull(roles);
        assertEquals("admin", roles.get(0).getRIGHTGROUPID());
    }
}
