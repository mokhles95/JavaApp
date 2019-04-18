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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

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

    BidService bidService = BidService.getInstance();
    SwitchView switchView = SwitchView.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Bid> bids = bidService.displayBids();
        try {

            for (Bid bid : bids) {
                //System.out.println(bid.getId());
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

    /**
     * When this method is called, it will change the Scene to a TableView
     * example
     *
     * public void changeScreenButtonPushed(ActionEvent event) throws
     * IOException { Parent tableViewParent =
     * FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); Scene
     * tableViewScene = new Scene(tableViewParent);
     *
     * //This line gets the Stage information Stage window = (Stage) ((Node)
     * event.getSource()).getScene().getWindow();
     *
     * window.setScene(tableViewScene); window.show(); }
     *
     */
}
