/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bookmark;

import Entities.Bookmark;
import Services.Implementation.BookmarkService;
import Tools.AlertHelper;
import Tools.CurrentDate;
import Tools.SwitchView;
import Tools.UserSessions;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddBookmarkController implements Initializable {

    @FXML
    private JFXButton cancelAdd;
    @FXML
    private JFXButton addBookmarkButton;
    @FXML
    private Label dateValue;
    @FXML
    private Label dateLabel;
    
    BookmarkService bookmarkService = BookmarkService.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cancelAdd(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void addBookmark(ActionEvent event) {

        Window owner = addBookmarkButton.getScene().getWindow();
        
        Date bookmarkDate = CurrentDate.getCurrentDate();
        System.out.println(bookmarkDate.toString());
        int bookmarkerId = UserSessions.getInstance().getLoggedInUser().getId();
        System.out.println(bookmarkerId);
        Bookmark bookmark = new Bookmark(bookmarkerId,3,bookmarkDate);
        if (bookmarkService.addBookmark(bookmark)) {
            SwitchView.getInstance().closeWindow(addBookmarkButton); 
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Error");
        }

    }
    
}
