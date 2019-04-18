/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class SwitchView {
private static SwitchView switchViewInstance;

    private SwitchView() {
    }

    public static SwitchView getInstance() {
        if (switchViewInstance == null) {
            switchViewInstance = new SwitchView();
        }
        return switchViewInstance;
    }  
    
    public void newWindow(String sourceFile, String windowName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(sourceFile));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.setScene(new Scene(rootAdmin));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
    
    public void newWindow(String sourceFile, String windowName, int id) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(sourceFile));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.setScene(new Scene(rootAdmin));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
    
}
