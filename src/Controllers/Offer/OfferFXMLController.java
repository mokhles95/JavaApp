/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Offer;

import Tools.AlertHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class OfferFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnAddOffer;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void AddOffer (ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        if(event.getSource()==btnAddOffer){
          stage = new Stage();
          root = FXMLLoader.load(getClass().getResource("/GUI/Offer/FormOffer.fxml"));
          stage.setScene(new Scene(root));
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.initOwner(btnAddOffer.getScene().getWindow());
          stage.showAndWait();
          
          
        }
        else{
            stage = (Stage) btnAddOffer.getScene().getWindow();
            stage.close();
            
        }
        
    }
}
