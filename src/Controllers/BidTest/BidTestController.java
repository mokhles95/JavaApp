/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.BidTest;

/**
 *
 * @author asus
 */
import Entities.Bid;
import Services.Implementation.BidService;
import Services.Implementation.BookmarkService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class BidTestController implements Initializable {

    //configure the table
    @FXML
    private TableView<Bid> tableView;
    @FXML
    private TableColumn<Bid, Integer> deliveryTimeColumn;
    @FXML
    private TableColumn<Bid, Integer> minimalRateColumn;
    @FXML
    private TableColumn<Bid, Integer> projectIdColumn;

    @FXML
    private Button detailedPersonViewButton;
    

    /**
     * This method will allow the user to double click on a cell and update the
     * first name of the person
     */
    @FXML
    public void changeDeliveryTimeCellEvent(CellEditEvent edittedCell) {
        Bid personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setDeliveryTime(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    /**
     * This method will enable the detailed view button once a row in the table
     * is selected
     */
    @FXML
    public void userClickedOnTable() {
        this.detailedPersonViewButton.setDisable(false);
    }

    /**
     * This method will allow the user to double click on a cell and update the
     * first name of the person
     */
    @FXML
    public void changeMinimalRateColumnCellEvent(CellEditEvent edittedCell) {
        Bid personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setMinimalRate(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    /**
     * When this method is called, it will change the Scene to a TableView
     * example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * When this method is called, it will pass the selected Person object to a
     * the detailed view
     *
     * public void changeSceneToDetailedPersonView(ActionEvent event) throws
     * IOException { FXMLLoader loader = new FXMLLoader();
     * loader.setLocation(getClass().getResource("PersonView.fxml")); Parent
     * tableViewParent = loader.load();
     *
     * Scene tableViewScene = new Scene(tableViewParent);
     *
     * //access the controller and call a method PersonViewController
     * controller = loader.getController();
     * controller.initData(tableView.getSelectionModel().getSelectedItem());
     *
     * //This line gets the Stage information Stage window = (Stage) ((Node)
     * event.getSource()).getScene().getWindow();
     *
     * window.setScene(tableViewScene); window.show(); }
     *
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        minimalRateColumn.setCellValueFactory(new PropertyValueFactory<>("minimalRate"));
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
        deliveryTimeColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryTime"));

        //load dummy data
        tableView.setItems(getBids());

        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        // minimalRateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // deliveryTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //This will allow the table to select multiple rows at once
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Disable the detailed person view button until a row is selected
        //this.detailedPersonViewButton.setDisable(true);
    }

    /**
     * This method will remove the selected row(s) from the table
     */
    @FXML
    public void deleteButtonPushed() {
        BidService bidService = new BidService();

        ObservableList<Bid> selectedRows, allBids;
        allBids = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bid objects from the table
        for (Bid bid : selectedRows) {
            System.out.println(bid.toString());
            bidService.deleteBid(bid);
            allBids.remove(bid);
        }
    }

    /**
     * This method will create a new Bid object and add it to the table
     *
     * public void newPersonButtonPushed() { Bid newPerson = new
     * Bid(firstNameTextField.getText(), lastNameTextField.getText(),
     * birthdayDatePicker.getValue());
     *
     * //Get all the items from the table as a list, then add the new person to
     * //the list tableView.getItems().add(newPerson); }
     *
     */
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<Bid> getBids() {
        ObservableList<Bid> bids = FXCollections.observableArrayList();
        BidService bidService = new BidService();
        ArrayList<Bid> bid;
        bid = bidService.displayBids();
        bids.addAll(bid);
        return bids;
    }
}
