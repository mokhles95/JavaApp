/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Authentication;

import Entities.User;
import Services.Implementation.AuthenticationService;
import Tools.AlertHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class AuthenticationController implements Initializable {

    @FXML
    private TextField loginEmailText;
    @FXML
    private PasswordField loginPasswordText;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwordText;
    
    
    @FXML
    private Button signupBtn;
    
    AuthenticationService authenticationService = AuthenticationService.getInstance();
    @FXML
    private PasswordField repeatPasswordText;
    @FXML
    private TextField usernameText;
    @FXML
    private RadioButton employerType;
    @FXML
    private RadioButton freelancerType;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        Window owner = loginBtn.getScene().getWindow();
        if(loginEmailText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your email");
            return;
        }
        if(loginPasswordText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your password");
            return;
        }
        if (authenticationService.login(emailText.getText(), passwordText.getText()))
        {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!", 
                "Welcome ");
        }
        else{
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Error!", 
                "Verify Credentials ");
        }
    }
    
     @FXML
    private void signup(ActionEvent event) {
        Window owner = signupBtn.getScene().getWindow();
        if(emailText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your email");
            return;
        }
       
       if(passwordText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your password");
            return;
        }
       
        if(freelancerType.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your password");
            return;
        }
        
        if(usernameText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your password");
            return;
        }
        
        if(repeatPasswordText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your password again");
            return;
        }
        
        
        if (!repeatPasswordText.getText().equals(passwordText.getText()))
        {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Your password and password confirmation must be the same");
        }else
        {
            BooleanProperty type = employerType.selectedProperty();
            User user = new User(emailText.getText(),type);
            if (authenticationService.register(user))
        {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!", 
                "Welcome ");
        }
        else{
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Error!", 
                "Verify Credentials ");
        }
        }
        

    }
    

    
}
