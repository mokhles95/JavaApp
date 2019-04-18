/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Offer;

import Entities.Offer;
import Services.Implementation.OfferService;
import Services.Interface.OfferServiceInterface;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;



import javafx.scene.control.TableView;
public class FormOfferController implements Initializable {
    
     @FXML
    private TextField txtMaxPrice;

    @FXML
    private TextField txtJobName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtMinPrice;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtType;
    
     @FXML
    private TableView<Offer> tab;
    
    OfferService os = new OfferService();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }

@FXML
    private void AddNewOffer() throws SQLException {
        os=new OfferService();
        int priceMin= Integer.parseInt(txtMinPrice.getText());
        int priceMax= Integer.parseInt(txtMaxPrice.getText());
        String status = "En Attente";

        Offer o=new Offer(txtJobName.getText(),priceMin , priceMax, txtType.getText(), txtDescription.getText(),txtLocation.getText(),status);
     
        os.create(o);
        tab.setItems(os.OfferList());
        tab.setEditable(true);
        tab.refresh();
    }    
    
}
