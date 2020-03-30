/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Dashboard;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Dashboard_FreelancerController implements Initializable {

    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton bnthome;
    @FXML
    private JFXButton list_espace;
    @FXML
    private JFXButton list_tickets2;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private ImageView profilephoto;
    @FXML
    private JFXButton listBookmarks;
    @FXML
    public AnchorPane windowPane;
    @FXML
    private JFXButton bidNavigation;
    @FXML
    public Pane content;
    @FXML
    private Label usernameHeader;
    @FXML
    private Label name1;
    @FXML
    private Label usernameNav;
    
      @FXML
    private JFXButton skills;
    @FXML
    private JFXButton projectList;
    @FXML
    private JFXButton jobList;
    
    public void setUserName(String username)
    {
        this.usernameNav.setText(username);
        this.usernameHeader.setText(username);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    

    
    

    @FXML
    private void openprofile(ActionEvent event) {
    }

    @FXML
    private void openhome(ActionEvent event) {
    }


    @FXML
    private void openlist_espace(ActionEvent event) {
    }

    @FXML
    private void openlist_tickets(ActionEvent event) {

        try {
            loadViewContent("/GUI/Project/Project.fxml", windowPane, content);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    public void displayBidList(ActionEvent event) {
        try {
            loadViewContent("/GUI/Bid/Bid.fxml", windowPane, content);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void displayBookmarkList(ActionEvent event) {

        try {
            loadViewContent("/GUI/Bookmark/Bookmark.fxml", windowPane, content);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadViewContent(String path, AnchorPane root, Pane content) throws IOException {
        content = FXMLLoader.load(getClass().getResource(path));
        root.getChildren().set(3, content);
        content.setLayoutX(250);
        content.setLayoutY(150);
    }
    
     

    @FXML
    private void openSkills(ActionEvent event) {
                try {
            loadViewContent("/GUI/Skills/skillsFXML.fxml", windowPane, content);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void reviewList(ActionEvent event) {
         try {
            loadViewContent("/GUI/Review/Review.fxml", windowPane, content);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void jobList(ActionEvent event) {
         try {
            loadViewContent("/GUI/Job/AllJobsInterface.fxml", windowPane, content);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void projectList(ActionEvent event) {
        
    }


}
