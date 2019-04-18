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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EmployerOffersController implements Initializable {

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
    ObservableList<Offer> oblist ;
    @FXML
    private TableColumn<Offer, Integer> colId;
    OfferService os =new OfferService();
    @FXML
    private Button btnAbdate;

    @FXML
    private TextField textSalary;


    int i=0;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtName;
    @FXML
    private TextField textType;
    @FXML
    private TextField textLocation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView();
        
        
    }  
    
    public void tableView()
    {
        oblist=FXCollections.observableArrayList();
        oblist=os.AllOffer();
        tableView.setItems(oblist);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("priceMax"));
        tableView.refresh();
    }

    @FXML
   public int update(ActionEvent event) {
       
       
       if(tableView.getSelectionModel().getSelectedItem().getStatus().equals("En attente")){
           i=1;
         os=new OfferService();
        Offer o=tableView.getSelectionModel().getSelectedItem();
        txtName.setText(o.getName());
        textType.setText(o.getType());   
        textSalary.setText("" +o.getPriceMax());
        textLocation.setText(o.getLocation());
       
       }
       else 
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Error");
       alert.setHeaderText(null);
       alert.setContentText("This offer is already accepted");
       alert.show();
           
       }
       
         
        
       return i;
    }

    @FXML
    private void add(ActionEvent event) {
       Offer o=tableView.getSelectionModel().getSelectedItem();        
       Offer o1;
       o1 =new Offer(o.getId(),txtName.getText(), textType.getText(), textLocation.getText(), Integer.parseInt(textSalary.getText()));
       os.update(o1,o.getId());
       txtName.setText("");
       textType.setText("");
       textLocation.setText("");
       textSalary.setText("");
      
       tableView.setItems(os.AllOffer());
       tableView.setEditable(true);
       tableView.refresh();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Success");
       alert.setHeaderText(null);
       alert.setContentText("Job updated with success!");
       alert.show();
       i=0;   
       
            
    }

    @FXML
    private void delete(ActionEvent event) {
        
          Offer o =tableView.getSelectionModel().getSelectedItem();
     
        os.deleteOffer(o.getId());
         this.tableView.setItems(os.AllOffer());
        tableView.setEditable(true);
        tableView.refresh();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("Offer deleted");
        alert.show();
    }
    
    
    
    
}