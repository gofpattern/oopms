package openones.oopms.dms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CommonUtil {
    
   public static boolean isNumber (String a) {
      try {
          if(a.matches("^(([1-9]*)|(([1-9]*).([0-9]*)))$)")) {
              return true;
          } 
          return false;
      } catch (Exception e) {
          return false;
      }
       
      
   }
   
    

  public static boolean  isValidDate(String input) {
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
     try{ 
         format.parse(input);
         
         return true;
         }
     catch(ParseException e){
         return false;
         }
   }
   
}