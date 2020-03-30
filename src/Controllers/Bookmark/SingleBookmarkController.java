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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    BookmarkService bookmarkService = BookmarkService.getInstance();
    BidService bidService = BidService.getInstance();

    @FXML
    private JFXButton deleteBookmark;

    private Label id = new Label();

    @FXML
    private Label projectMinBungetValue;
    @FXML
    private Label projectMaxBungetValue;
    @FXML
    private JFXButton seeProject;

    @FXML
    private Label expirationTime;
    @FXML
    private Label publicationDate;
    @FXML
    private Label projectPublicationDateValue;
    @FXML
    private Label projectExpirationTime;

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
        projectExpirationTime.setText(String.valueOf(project.getValidityPeriod()));
        projectPublicationDateValue.setText(String.valueOf(project.getValidityPeriod()));

        id.setText(String.valueOf(bookmark.getId()));

    }

    @FXML
    private void seeProject(ActionEvent event) {
    }

    @FXML
    private void deleteBookmark(ActionEvent event) {
        this.alertDelete("confirmation", "DELETE", "do you really want to delete this bookmark ?");
    }

    private void alertDelete(String title, String header, String context) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            bookmarkService.deleteBookmark(Integer.valueOf(id.getText()));
        } else {
            alert.close();
        }
    }

}
