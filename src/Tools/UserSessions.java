/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Controllers.Dashboard.Dashboard_FreelancerController;
import Entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Dell
 */
public class UserSessions {
    
     User loggedInUser;

    static UserSessions instance;
    
    private UserSessions()
    {

    }
    

    public User getLoggedInUser() {return loggedInUser;}

    public void setLoggedInUser(User loggedInUser) {this.loggedInUser = loggedInUser;}



    public static UserSessions getInstance() 
    {
        if(instance == null)
        {
            instance = new UserSessions();
            return instance;
        }
        else
            return instance;
    }

   
    
}
