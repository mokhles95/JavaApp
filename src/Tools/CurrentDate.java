/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
import java.sql.Date;


/**
 *
 * @author asus
 */
public class CurrentDate {
    
    private CurrentDate(){}
    
    public static Date getCurrentDate(){
        long millis=System.currentTimeMillis();  
        Date date=new Date(millis); 
        return date; 
         
    }
}
