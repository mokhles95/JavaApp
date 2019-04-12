/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bookmark;

import Entities.Bid;
import Entities.Bookmark;
import Services.Implementation.BidService;
import Services.Implementation.BookmarkService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BookmarkFXMLController implements Initializable {

    @FXML
    private TableView<Bookmark> tableView;
    @FXML
    private TableColumn<Bookmark, Integer> projectIdColumn;
    @FXML
    private TableColumn<Bookmark, Date> dateAddedColumn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("project_id"));
        tableView.setItems(getBookmarks());
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    private void changeScreenButtonPushed(ActionEvent event) {
    }

    @FXML
    private void userClickedOnTable(MouseEvent event) {
    }

    @FXML
    public void deleteButtonPushed() {
        BookmarkService bookmarkService = new BookmarkService();
        ObservableList<Bookmark> selectedRows, allBookmarks;
        allBookmarks = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Bid objects from the table
        for (Bookmark bookmark : selectedRows) {
            bookmarkService.deleteBookmark(bookmark);
            allBookmarks.remove(bookmark);
        }
    }

    public ObservableList<Bookmark> getBookmarks() {
        ObservableList<Bookmark> bookmarks = FXCollections.observableArrayList();
        BookmarkService bookmarkService = new BookmarkService();
        ArrayList<Bookmark> bookmark;
        bookmark = bookmarkService.displayBookmarks();
        bookmarks.addAll(bookmark);
        //System.out.println(bookmarks.toString());
        return bookmarks;
    }
}
