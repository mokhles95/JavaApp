/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Dashboard_EmployerController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton list_events;
    @FXML
    private JFXButton list_organi;
    @FXML
    private JFXButton list_espace;
    @FXML
    private JFXButton list_tickets;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private VBox pnl_scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openhome(ActionEvent event) {
    }

    @FXML
    private void openlist_events(ActionEvent event) {
    }

    @FXML
    private void openlist_organi(ActionEvent event) {
    }

    @FXML
    private void openlist_espace(ActionEvent event) {
    }

    @FXML
    private void openlist_tickets(ActionEvent event) {
    }

    @FXML
    private void openprofile(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
