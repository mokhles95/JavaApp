/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.MokhlessRegister;

import Controllers.Dashboard.Dashboard_FreelancerController;
import Entities.User;
import Services.Implementation.ServiceUser;
import Tools.AlertHelper;
import Tools.UserSessions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author asus
 */
public class LoginController {

    @FXML
    private TextField loginEmailText;
    @FXML
    private PasswordField loginPasswordText;
    @FXML
    private Button loginBtn;

    ServiceUser userService = ServiceUser.getInstance();

    @FXML
    private void login(ActionEvent event) {
        Window owner = loginBtn.getScene().getWindow();
        if (loginEmailText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }
        if (loginPasswordText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }
        try {
            User u = userService.getUserByUsername(loginEmailText.getText());
            if (u != null) {
                if (BCrypt.checkpw(loginPasswordText.getText(), u.getPassword().substring(0, 2) + "a" + u.getPassword().substring(3))) {

                    UserSessions.getInstance().setLoggedInUser(u);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Dashboard/Dashboard_Freelancer.fxml"));
                    Parent rootAdmin = (Parent) fxmlLoader.load();
                    Dashboard_FreelancerController dfc = fxmlLoader.getController();
                    dfc.setUserName(u.getUsername());
                    Stage stage = new Stage();
                    
                    stage.setScene(new Scene(rootAdmin));
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.show();

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Connexion échoué !");
                alert.setContentText("Votre Nom d'utilisateur/Mot de passe sont incorrects ! Merci de les vérifier");
                alert.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
