package openones.oopms.controller;

import org.apache.log4j.Logger;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
@RequestMapping("VIEW")
public class PlannerController {
    /** Logger for logging. */  
    private static Logger log = Logger.getLogger(PlannerController.class);  
  
    /** 
     * Default screen. 
     * @return name of view which is the name of the JSP page. 
     */  
    @RequestMapping  
    public String initScreen() {  
        log.debug("initScreen.START");  
        // Display TaskManager.jsp  
        return "TaskManager";  
    }  
}
