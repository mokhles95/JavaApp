/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Skills;

import Services.Implementation.SkillService;
import Tools.SkillsAPI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author q
 */
public class SkillsFXMLController implements Initializable {

    private static final String ITEM = "Item ";
    private int counter = 0;
    @FXML
    private AnchorPane anchorPane;
    Label labelSelected;
    @FXML
    private Label message;
    @FXML
    private Pane mainPane;

    JFXListView<Label> list = new JFXListView<>();
    FlowPane pane = new FlowPane();
    VBox box = new VBox();
    StackPane main = new StackPane();
    @FXML
    private JFXButton deleteSkillBtn;
    @FXML
    private JFXButton addSkillBtn;
    @FXML
    private JFXButton allSkillsbtn;
    @FXML
    private JFXButton mySkillsBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // SKILLS API CALL
            List<String> listLabels = SkillsAPI.getSkills();

            for (int i = 0; i < listLabels.size(); i++) {
                list.getItems().add(new Label(listLabels.get(i)));
            }
            list.getStyleClass().add("mylistview");

            list.depthProperty().set(1);
            list.setExpanded(true);

            pane.setStyle("-fx-background-color:WHITE");

            AnchorPane listsPane = new AnchorPane();
            listsPane.getChildren().add(list);
            AnchorPane.setLeftAnchor(list, 100.0);

            box.getChildren().add(pane);
            box.getChildren().add(listsPane);
            box.setSpacing(40);

            main.getChildren().add(box);

            mainPane.getChildren().add(main);

        } catch (IOException ex) {
            Logger.getLogger(SkillsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addSkill(ActionEvent event) {
        String name = list.getSelectionModel().getSelectedItem().getText();
        message.setText("A Skill is Added !");
        message.setVisible(SkillService.getInstance().addSkill(name));

    }

    @FXML
    private void allSkills(ActionEvent event) throws IOException {
        list.getItems().clear();

        List<String> listLabels = SkillsAPI.getSkills();

        for (int i = 0; i < listLabels.size(); i++) {
            list.getItems().add(new Label(listLabels.get(i)));
        }

        mySkillsBtn.setVisible(true);
        allSkillsbtn.setVisible(false);
        addSkillBtn.setVisible(true);
        deleteSkillBtn.setVisible(false);

    }

    @FXML
    private void mySkills(ActionEvent event) {

        list.getItems().clear();
        // SKILLS API CALL
        List<String> mylist = SkillService.displaySkills();

        for (int i = 0; i < mylist.size(); i++) {
            list.getItems().add(new Label(mylist.get(i)));
        }
        
        mySkillsBtn.setVisible(false);
        allSkillsbtn.setVisible(true);
        addSkillBtn.setVisible(false);
        deleteSkillBtn.setVisible(true);

    }

    @FXML
    private void deleteSkill(ActionEvent event) {
        
        String name = list.getSelectionModel().getSelectedItem().getText();
        message.setText("A Skill is Deleted !");
        message.setVisible(SkillService.getInstance().deleteSkill(name));
        Label id = list.getSelectionModel().getSelectedItem();
        list.getItems().remove(id);

    }
               

}
