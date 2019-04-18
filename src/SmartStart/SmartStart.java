package SmartStart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Services.Implementation.OfferService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author asus
 */
public class SmartStart extends Application {

 private Stage stage;
    private static SmartStart instance;
    private Scene scene;
    public SmartStart() throws IOException, InterruptedException {
        instance = this;
        scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Offer/OfferList.fxml")));
    }
     public static SmartStart getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
       this.stage = stage;
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.centerOnScreen();
        stage.show(); 
    }
public void changescene(Scene scene) {
        this.scene = scene;
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //OfferService os = new OfferService();
        //Offer o = new Offer("Ahmed el din", 558, 55555, "aaaa", "aaaa", "dazdazd", "En attente");
        //os.OfferList();
        
        launch(args);
          
    }
}
