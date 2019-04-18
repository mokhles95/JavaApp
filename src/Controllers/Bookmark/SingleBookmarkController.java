/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bookmark;

import Entities.Bookmark;
import Entities.Project;
import Services.Implementation.BidService;
import Services.Implementation.BookmarkService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SingleBookmarkController implements Initializable {

    @FXML
    private AnchorPane singleBidContentPane;
    @FXML
    private Pane singleBidPane;
    @FXML
    private Label projectTitleValue;
    private Label deliveryTimeValue;
    @FXML
    private JFXButton updateBid;
    @FXML
    private JFXButton deleteBid;
    @FXML
    private Label dateAddedLabel;
    @FXML
    private Label dateAddedValue;

    BookmarkService bookmarkService = BookmarkService.getInstance();
    BidService bidService = BidService.getInstance();

    @FXML
    private Label projectMinBungetValue;
    @FXML
    private Label projectMaxBungetValue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void transferSingleBookmark(Bookmark bookmark) {
        Project project = bidService.findProjectById(bookmark.getProjectId());
        projectTitleValue.setText(project.getProjectName());
        projectMinBungetValue.setText(String.valueOf(project.getMinBudget()));
        projectMaxBungetValue.setText(String.valueOf(project.getMaxBudget()));
        dateAddedValue.setText(String.valueOf(bookmark.getDateAdded()));

    }

    @FXML
    private void updateBid(ActionEvent event) {
    }

    @FXML
    private void deleteBid(ActionEvent event) {
    }

}
