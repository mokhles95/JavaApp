/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bid;

import Entities.Bid;
import Services.Implementation.BidService;
import Utils.AlertHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author asus
 */
public class AddBidController {

    private JFXTextField reference;
    private JFXTextField libelle;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField minimalRate;
    @FXML
    private JFXTextField deliveryTime;
    BidService bidService = BidService.getInstance();

    @FXML
    public void AddBid(ActionEvent event) throws IOException {
        Window owner = ajouter.getScene().getWindow();
        Bid bid = new Bid();
        //if (controle.controleDeSaisie(owner)) {

        bid = new Bid();
        bidService.addBid(bid);

        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

    @FXML
    private void retourAction(MouseEvent event) {
    }

    @FXML
    private void cancelAdd(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

    }

}
