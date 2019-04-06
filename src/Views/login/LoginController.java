/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.login;

import Tools.BCrypt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        @FXML
    private void login(ActionEvent event) throws Exception {
        nb++;
        System.out.println(nb);
        //UserService us = new UserService();
//            user = new User();
        String username = nom.getText();
        String password = pwd.getText();
        String code = codeL.getText();
        String code2 = "1234";
        int x = SmsService.x;
        User u = us.findUserByUsername(username);
//                currentUser = u;
        SmsService sms = new SmsService();
        user = u;

        if (u != null) {

            if (u.getEnabled() == true) {

                StringBuilder finalpassword = new StringBuilder(u.getPassword());
                finalpassword.setCharAt(2, 'a');
                String pass = finalpassword.toString();
                if (BCrypt.checkpw(password, pass)) {
                    if (nb == 1) {
                     sms.sendSms(x);
                    }
                    System.out.println(x);

                    if (code.equals(Integer.toString(x))) {
                        Stage stage = (Stage) log.getScene().getWindow();
                        if (u.getRoles().equals("user")) {
                            Parent root = FXMLLoader.load(getClass().getResource("/mnd/gui/Ui.fxml"));
                            nb=0;
                            stage.close();
                            stage = new Stage();
                            Scene scene = new Scene(root);
                            scene.setFill(Color.TRANSPARENT);
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.show();
                        } else {
                            Parent root = FXMLLoader.load(getClass().getResource("/mnd/gui/AdminUi.fxml"));
                            nb=0;
                            stage.close();
                            stage = new Stage();
                            Scene scene = new Scene(root);
                            scene.setFill(Color.TRANSPARENT);
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.show();
                        }
                    } else {
                        System.out.println("false code");
                    }
                } else {
//                mdpStatus.setText("mot de passe éronné");
                    pwd.setStyle("-fx-border-color:red;-fx-background-color:transparant;-fx-border-width:0px0px2px0px;-fx-font-size:1.4em");
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("vous etes bannis");

                alert.showAndWait();
            }
        } else {
//            userStatus.setText("utilisateur n'existe pas");
            nom.setStyle("-fx-border-color:red;-fx-background-color:transparant;-fx-border-width:0px0px2px0px;-fx-font-size:1.4em");
        }

    }
    
}
