/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Offer;

import Tools.UserSession;
import Utils.Server;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ChatController implements Initializable {
         public static ArrayList<Thread> threads;

    @FXML
    private GridPane gridPaneChat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             try {
                 threads = new ArrayList<Thread>();
                 //////////////////////////////////////////////////////////////////////////////////////////////
                 // idhaken awel marra 5alliha sinon commentiha
//                 TextField portTextField = new TextField("8888");
//                 System.out.println(threads.size());
//                 Utils.Server server = new Utils.Server(Integer.parseInt(portTextField
//                         .getText()));
//                 Thread serverThread = (new Thread(server));
//                 serverThread.setName("Server Thread");
//                 serverThread.setDaemon(true);
//                 if(!serverThread.isAlive()){
//                     serverThread.start();
//                 }
//                 threads.add(serverThread);
                 //////////////////////////////////////////////////////////////////////////////////////////////
                 
                 Utils.Client client;
                 TextField nameField = new TextField(UserSession.User.getUsername());
                 TextField hostNameField = new TextField("localhost");
                 TextField portNumberField = new TextField("8888");
                 hostNameField.setDisable(true);
                 portNumberField.setDisable(true);
                 nameField.setDisable(true);
                 client = new Utils.Client(hostNameField.getText(), Integer
                         .parseInt(portNumberField.getText()), nameField
                                 .getText());
                 Thread clientThread = new Thread(client);
                 clientThread.setDaemon(true);
                 clientThread.start();
                 threads.add(clientThread);
                 /* Make the root pane and set properties */
                 gridPaneChat.setPadding(new Insets(20));
                 gridPaneChat.setAlignment(Pos.CENTER);
                 gridPaneChat.setHgap(10);
                 gridPaneChat.setVgap(10);
                 
                 /*
                 * Make the Chat's listView and set it's source to the Client's chatLog
                 * ArrayList
                 */
                 ListView<String> chatListView = new ListView<String>();
                 chatListView.setItems(client.chatLog);
                 chatListView.setPrefHeight(420);
                 chatListView.setPrefWidth(846.0);
                 
                 /*
                 * Make the chat text box and set it's action to send a message to the
                 * server
                 */
                 TextField chatTextField = new TextField();
                 chatTextField.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                         // TODO Auto-generated method stub
                         client.writeToServer(chatTextField.getText());
                         chatTextField.clear();
                     }
                 });
                 
                 /* Add the components to the root pane */
                 gridPaneChat.add(chatListView, 0, 0);
                 gridPaneChat.add(chatTextField, 0, 1);
             } catch (IOException ex) {
                 Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }    

}
