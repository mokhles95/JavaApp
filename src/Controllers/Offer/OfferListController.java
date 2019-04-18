/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Offer;

import Entities.Offer;
import Services.Implementation.OfferService;
import Services.Implementation.Vars;
import java.io.IOException;
import java.net.URL;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class OfferListController implements Initializable {
    
      @FXML
    private TableColumn<Offer, String> colId;
    
    
    
     @FXML
    private TableColumn<Offer, String> colOffer;

    @FXML
    private TableColumn<Offer, String> colType;

    ObservableList<Offer> oblist ;
    
    OfferService os =new OfferService();
    @FXML
    private TableView<Offer> tablelist;

     @FXML
    private TableColumn<Offer, Integer> colSalary;
     
      @FXML
    private Button btnDetail;

    @FXML
    private Button btnDlete;
    
    
    @FXML
    private TextField txtsearch;
    
     @FXML
    private Button btnHistory;
    private InvalidationListener listener;


     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        tableView();
        
   
           btnDetail.setOnAction( event -> {
          
                Vars.offer=oblist.get(tablelist.getSelectionModel().getSelectedIndex());
               
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("OfferDetail.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(OfferListController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     });
           
           
           
            btnHistory.setOnAction( event -> {
                         
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("History.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(OfferListController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     });
        
                  
        
        
        
        }

 @FXML
    void deleteOffer(ActionEvent event) throws ClassNotFoundException {
         Offer o =tablelist.getSelectionModel().getSelectedItem();
     
        os.deleteOffer(o.getId());
         this.tablelist.setItems(os.OfferList());
        tablelist.setEditable(true);
        tablelist.refresh();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("Offer deleted");
        alert.show();
   
        

    }  
    
    public void tableView()
    {
        oblist=FXCollections.observableArrayList();
        oblist=os.OfferList();
        tablelist.setItems(oblist);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colOffer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("priceMax"));
        tablelist.refresh();
    }

    @FXML
    private void search(KeyEvent event) {
    }
    
    
    
    
    
    
}
