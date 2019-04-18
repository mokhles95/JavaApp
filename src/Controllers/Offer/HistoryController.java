/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Offer;

import Entities.Offer;
import Services.Implementation.OfferService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Asus
 */




public class HistoryController implements Initializable {
    
    @FXML
    private TableColumn<Offer, Integer> colId;

    @FXML
    private TableView<Offer> tableView;
    @FXML
    private TableColumn<Offer, String> colName;
    @FXML
    private TableColumn<Offer, String> colType;
    @FXML
    private TableColumn<Offer, Integer> colSalary;
    @FXML
    private TableColumn<Offer, String> colLocation;
    @FXML
    private TableColumn<Offer, String> colStatus;
   
     @FXML
    private Button btnDelete;
            
    OfferService os =new OfferService();
  
    
    ObservableList<Offer> oblist ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tableView();
        // TODO
    } 
    
    
     public void tableView()
    {
        
        oblist=FXCollections.observableArrayList();
        oblist=os.OfferListHistory();
        tableView.setItems(oblist);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        colSalary.setCellValueFactory(new PropertyValueFactory<>("priceMax"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        tableView.refresh();
    }
    
     @FXML
    void delete(ActionEvent event) throws ClassNotFoundException {
         Offer o =tableView.getSelectionModel().getSelectedItem();
     
        os.deleteOffer(o.getId());
         this.tableView.setItems(os.OfferListHistory());
        tableView.setEditable(true);
        tableView.refresh();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés");
        alert.setHeaderText(null);
        alert.setContentText("Offre supprimé avec succés!");
        alert.show();
   
        

    }  
}
