/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bookmark;

import Controllers.Bid.SingleBidController;
import Entities.Bid;
import Entities.Bookmark;
import Services.Implementation.BidService;
import Services.Implementation.BookmarkService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BookmarkController implements Initializable {

    private TableView<Bookmark> tableView;
    @FXML
    private AnchorPane containerBidsAnchor;
    @FXML
    private VBox dynamicVbox;
    
    BookmarkService bookmarkService =  BookmarkService.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Bookmark> bookmarks = bookmarkService.displayBookmarks();
        try {

            for (Bookmark bookmark : bookmarks) {
                dynamicVbox.getChildren().add(addBookmarkView(bookmark));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public Parent addBookmarkView(Bookmark bookmark) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/Bookmark/SingleBookmark.fxml"));
        Parent root = (Parent) loader.load();
        SingleBookmarkController singleBookmarkController = loader.getController();
        singleBookmarkController.transferSingleBookmark(bookmark);
        return root;
    }

    public void deleteButtonPushed() {
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
        ArrayList<Bookmark> bookmark;
        bookmark = bookmarkService.displayBookmarks();
        bookmarks.addAll(bookmark);
        //System.out.println(bookmarks.toString());
        return bookmarks;
    }
}
