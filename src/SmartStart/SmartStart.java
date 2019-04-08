package SmartStart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Bid;
import Entities.Bookmark;
import Services.Implementation.BidService;
import Services.Implementation.BookmarkService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class SmartStart extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/Authentication/AuthenticationDocument.fxml"));
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        BookmarkService bookmarkService = BookmarkService.getInstance();
        BidService bidService = BidService.getInstance();

        Bookmark bookmark = new Bookmark(4,1,1);
        System.out.println(bookmarkService.addBookmark(bookmark));
        
        Bid bid = new Bid(4,1,1);
        System.out.println(bidService.addBid(bid));
        bidService.deleteBid(1);
        
        
        
    }
    
}
