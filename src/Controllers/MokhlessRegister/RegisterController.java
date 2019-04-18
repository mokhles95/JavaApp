package Controllers.MokhlessRegister;

import Entities.FosUser;
import Entities.User;
import Services.Implementation.AuthenticationService;
import Tools.AlertHelper;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asus
 */
public class RegisterController {

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnSave;
    @FXML
    private PasswordField password1;

    @FXML
    private RadioButton employerType;
    @FXML
    private RadioButton freelancerType;

    AuthenticationService authenticationService = AuthenticationService.getInstance();

    @FXML
    private void HandleBtnSave(ActionEvent event) {
        Window owner = btnSave.getScene().getWindow();
        if (txtEmail.getText().isEmpty()) {
            Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }

        if (password.getText().isEmpty()) {
            Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }

        if (!employerType.isSelected() && !freelancerType.isSelected()){
            Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please select an account type");
            return;
        }

        if (txtUserName.getText().isEmpty()) {
            Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your txtUserName");
            return;
        }

        if (password1.getText().isEmpty()) {
            Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password again");
            return;
        }

        if (!password1.getText().equals(password.getText())) {
            Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Your password and password confirmation must be the same");
        }
        String accountType = "";

        if (employerType.isSelected()) {
            accountType += "employer";
        } else if (freelancerType.isSelected()) {
            accountType += "freelancer";
        }
//        BooleanProperty type = employerType.selectedProperty();
        User user = new User(txtEmail.getText(), accountType, txtUserName.getText(), password1.getText());
        if (authenticationService.login(txtEmail.getText(), txtUserName.getText()))
        {
             Utils.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "These crendetials already exist");
        }
        else if (authenticationService.register(user)) {
            Utils.AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                    "Welcome ");
        } else {
            Utils.AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Error!",
                    "Verify Credentials ");
        }
    }

    /*
    @FXML
    private void HandleBtnSave(ActionEvent event) throws IOException {
        Window owner = btnSave.getScene().getWindow();
        if (txtEmail.getText().isEmpty()) {
            Tools.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }

        if (password.getText().isEmpty()) {
            Tools.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }

        if (txtUserName.getText().isEmpty()) {
            Tools.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }
        String accountType = "";

        if (employerType.isSelected()) {
            accountType += "employer";
        } else if (freelancerType.isSelected()) {
            accountType += "freelancer";
        }
        User user = new User(emailText.getText(), accountType, txtUserName.getText(), txtUserName.getText());
        String msg = authenticationService.register(user);
        if (msg.isEmpty()) {
            Tools.AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                    "Welcome ");
        } else {
            Tools.AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Error!",
                    msg);
        }
    }
     */
}
