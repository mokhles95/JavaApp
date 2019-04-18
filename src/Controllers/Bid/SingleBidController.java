/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bid;

import Entities.Bid;
import Entities.Project;
import Services.Implementation.BidService;
import Tools.SwitchView;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author asus
 */
public class SingleBidController implements Initializable {
    SwitchView switchView = SwitchView.getInstance();

    BidService bidService = BidService.getInstance();
    @FXML
    private JFXButton updateBid;

    @FXML
    private Label minimalRateLabel;
    @FXML
    private Label deliveryTimeLabel;
    @FXML
    private Pane singleBidPane;
    @FXML
    private AnchorPane singleBidContentPane;
    @FXML
    private Label projectTitleValue;
    @FXML
    private Label deliveryTimeValue;
    @FXML
    private Label minimalRateValue;
    @FXML
    private JFXButton deleteBid;
    @FXML
    private Label projectMinBungetValue;
    @FXML
    private Label projectMaxBungetValue;
        
    private Label id = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    //Receive message from scene 1

    public void transferSingleBid(Bid bid) {
        Project project = bidService.findProjectById(bid.getProjectId());
        projectTitleValue.setText(project.getProjectName());
        projectMinBungetValue.setText(String.valueOf(project.getMinBudget()));
        projectMaxBungetValue.setText(String.valueOf(project.getMaxBudget()));
        deliveryTimeValue.setText(String.valueOf(bid.getDeliveryTime()));
        minimalRateValue.setText(String.valueOf(bid.getMinimalRate()));
        id.setText(String.valueOf(bid.getId()));
    }
    
    public void openEditWindow() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/Bid/EditBid.fxml"));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        EditBidController editBidController = fxmlLoader.getController();
        editBidController.loadData(Integer.parseInt(id.getText()));
        Stage stage = new Stage();
        stage.setTitle("Edit Bid");
        stage.setScene(new Scene(rootAdmin));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
    

    @FXML
    private void updateBid(ActionEvent event) throws IOException {
        this.openEditWindow();
    }

    @FXML
    private void deleteBid(ActionEvent event) {
    }
    
}
