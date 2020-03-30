/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bookmark;

import Entities.Bookmark;
import Services.Implementation.BookmarkService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BookmarkController implements Initializable {

    private TableView<Bookmark> tableView;
    @FXML
    private VBox dynamicVbox;
    
    BookmarkService bookmarkService =  BookmarkService.getInstance();
    @FXML
    private AnchorPane containerBidsAnchor;
    @FXML
    private JFXButton BookmarkButton;

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
        //    bookmarkService.deleteBookmark(bookmark);
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


    @FXML
    private void makeBookmark(ActionEvent event) throws IOException {
        this.openAddWindow();
    }

        public void openAddWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/Bookmark/AddBookmark.fxml"));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        
        AddBookmarkController addBookmarkController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Add Bookmark");
        stage.setScene(new Scene(rootAdmin));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

}
