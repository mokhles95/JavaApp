/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Offer;

import Entities.Offer;
import Services.Implementation.OfferService;
import Services.Implementation.Vars;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class OfferDetailController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private Label Desc;

    @FXML
    private Label status;

    @FXML
    private Label priceMin;

    @FXML
    private Label Type;

    @FXML
    private Label priceMax;
    
    
     @FXML
    private Button btnAccept;

    @FXML
    private Button btnReject;

     @FXML
    private Label location;
     
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        view();
    }


@FXML
    void AcceptOffer(ActionEvent event) throws SQLException {
        OfferService o=OfferService.getInstance();
        Offer of=  o.get(Vars.offer);
        o.updateAcceptedStatus(of, of.getId());
        view();
        
        

    }

    @FXML
    void RejectOffer(ActionEvent event) throws SQLException {
         OfferService o=OfferService.getInstance();
        Offer of=  o.get(Vars.offer);
        o.updateRejectedStatus(of, of.getId());
        view();

    }    
    
    public void view()
    {
         OfferService o=OfferService.getInstance();
        try {
          Offer of=  o.get(Vars.offer);
           name.setText(of.getName()); 
           Desc.setText(of.getDescription()); 
           status.setText(of.getStatus());
           
           priceMin.setText(of.getName());
           Type.setText(of.getType()); 
           location.setText(of.getLocation());
           
           

        } catch (SQLException ex) {
            Logger.getLogger(OfferDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
}
