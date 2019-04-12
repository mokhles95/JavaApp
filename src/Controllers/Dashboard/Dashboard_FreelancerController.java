/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Dashboard;

import Tools.AlertHelper;
import Tools.SwitchView;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Dashboard_FreelancerController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label name;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton list_events;
    @FXML
    private JFXButton list_organi;
    @FXML
    private JFXButton list_espace;
    @FXML
    private JFXButton list_tickets2;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private JFXButton listBids;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private ScrollPane dynamicNode;

    private String currentlyInDynamicPane;//not important
    @FXML
    private JFXButton listBookmarks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void openprofile(ActionEvent event) {
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
    private void logout(ActionEvent event) {
    }

    @FXML
    private void displayBidList(ActionEvent event) {
        //SwitchView switchView = new SwitchView();
        //switchView.switchView(dynamicNode);
        //Window owner = listBids.getScene().getWindow();
        try {
            Node node = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("GUI/BidTest/BidTest.fxml"));
            //.getClassLoader()

            dynamicNode.setContent(node);

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }

    }

    @FXML
    private void displayBookmarkList(ActionEvent event) {
        try {
            Node node = (Node) FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Bookmark/BookmarkFXML.fxml"));
            //.getClassLoader()
            dynamicNode.setContent(node);

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }

    }

}
