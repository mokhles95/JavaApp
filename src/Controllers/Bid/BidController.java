/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bid;

/**
 *
 * @author asus
 */
import Entities.Bid;
import Services.Implementation.BidService;
import Tools.SwitchView;
import Tools.UserSessions;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class BidController implements Initializable {

    @FXML
    private AnchorPane containerBidsAnchor;
    @FXML
    private VBox dynamicVbox;

    SwitchView switchView = SwitchView.getInstance();
    @FXML
    private JFXButton BidButton;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    this.initBids();

    }

    public  void initBids(){
         ArrayList<Bid>  bids = BidService.displayBids();
        try {
            for (Bid bid : bids) {
                dynamicVbox.getChildren().add(addBidView(bid));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Parent addBidView(Bid bid) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/Bid/SingleBid.fxml"));
        Parent root = (Parent) loader.load();
        SingleBidController singleBidController = loader.getController();
        singleBidController.transferSingleBid(bid);
        return root;
    }

    @FXML
    private void makeBid(ActionEvent event) throws IOException {
        this.openAddWindow();
    }

        public void openAddWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/Bid/AddBid.fxml"));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        
        AddBidController addBidController = fxmlLoader.getController();
        addBidController.loadData(2);
        Stage stage = new Stage();
        stage.setTitle("Add Bid");
        stage.setScene(new Scene(rootAdmin));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

}
