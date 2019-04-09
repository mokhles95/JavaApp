/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bid;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BidController implements Initializable {

    @FXML
    private TableView<?> BidsTable;
    @FXML
    private TableColumn<?, ?> ProjectNameColumn;
    @FXML
    private TableColumn<?, ?> MinimalRateColumn;
    @FXML
    private TableColumn<?, ?> DeliveryTimeColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    
}
