 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Review;

import Entities.FosUser;
import Entities.Project;
import Entities.Review;
import Entities.User;
import Services.Implementation.ReviewService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ReviewController implements Initializable {

    @FXML
    private TextArea comment;
    @FXML
    private TextArea nbre;
    @FXML
    private RadioButton yesTime;
    @FXML
    private RadioButton noTime;
    @FXML
    private Rating rating;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private RadioButton yesBudget;
    @FXML
    private RadioButton noBudget;
    @FXML
    private TableView<Review> tab;
    @FXML
    private TableColumn<Review, Integer> budgetTab;
    @FXML
    private TableColumn<Review, Integer> timeTab;
    @FXML
    private TableColumn<Review, Integer> rateTab;
    @FXML
    private TableColumn<Review, String> commentTab;

    ReviewService rs;
    
    @FXML
    private TableColumn<Review, Integer> idTab;

    @FXML
    private Button delete;
    
    @FXML
    private ComboBox<String> reviewed;

    @FXML
    private ComboBox<String> project;
     
    @FXML
    private Button report;
    
    int i=0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rs=new ReviewService();
        ObservableList<User> data = rs.AfficherFreelancers();
        for(i=0;i<data.size();i++){
        reviewed.getItems().addAll(data.get(i).getUsername());
        
        }
        //System.out.println(data.toString()+reviewed.toString());
          
        ObservableList<Project> data1 = rs.AfficherProject();
        for(i=0;i<data1.size();i++){
        project.getItems().addAll(data1.get(i).getProjectName());}
        //System.out.println(data1.toString()+project.toString());

        
        idTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        budgetTab.setCellValueFactory(new PropertyValueFactory<>("onBudget"));
        timeTab.setCellValueFactory(new PropertyValueFactory<>("onTime"));
        rateTab.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentTab.setCellValueFactory(new PropertyValueFactory<>("comment"));
        this.tab.setItems(rs.AfficherReview());
        tab.setEditable(true);
        tab.refresh();
        count();
      
        
    }    

    @FXML
    public void reportAction (ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if(event.getSource()==report){
          stage = new Stage();
          root = FXMLLoader.load(getClass().getResource("/GUI/Report/Report.fxml"));
          stage.setScene(new Scene(root));
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.initOwner(report.getScene().getWindow());
          stage.showAndWait();                    
        }
        else{
            stage = (Stage) report.getScene().getWindow();
            stage.close();
            
        }
        
    }
    @FXML
    private void add(ActionEvent event) throws SQLException {
        ObservableList<User> data = rs.AfficherFreelancers();
        //String rv= reviewed.getSelectionModel().getSelectedItem();
        ObservableList<Project> data1 = rs.AfficherProject();
        //String pj= project.getSelectionModel().getSelectedItem();
        int budget = 0;
        int time = 0;
        rs = new ReviewService();
        
        
        if (!yesBudget.isSelected() && !noBudget.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please check the budget radio button !");
            alert.show();
            return;
        }
        if (!yesTime.isSelected() && !noTime.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please check the time radio button !");
            alert.show();
            return;
        }
        if (comment.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please add a comment!");
            alert.show();
            return;
        }
        
        
        if(tab.getSelectionModel().getSelectedItem()==null||i==0)
        {
        if(yesBudget.isSelected())
            budget=1;
        else if (noBudget.isSelected())
            budget=0;
        if(yesTime.isSelected())
            time=1;
        else if (noTime.isSelected())
            time=0;
        int freelancerReviewed=7;
//     System.out.println(reviewed.getSelectionModel().getSelectedIndex());
        System.out.println(reviewed.getSelectionModel().getSelectedItem());
        System.out.println(project.getSelectionModel().getSelectedItem());
//      freelancerReviewed= data.indexOf(reviewed.getId());
        int proj=1;
//      proj= data1.indexOf(project.getId());
        Review r = new Review(budget,time, (int) rating.getRating(),comment.getText(),proj,freelancerReviewed);
        rs.AjoutReview(r);
        nbre.setText("");
        this.tab.setItems(rs.AfficherReview());
        tab.setEditable(true);
        tab.refresh();
        //reviewed.setValue("Select Freelancer");
        project.setValue("Select Project");
        yesBudget.setSelected(false);
        noBudget.setSelected(false);
        yesTime.setSelected(false);
        noTime.setSelected(false);
        rating.setRating(0);
        comment.setText("");
        // nbre.setText("5");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Review added with success!");
        alert.show();
        count();
        }
        else
        {                
                     
        Review r=tab.getSelectionModel().getSelectedItem();
        if(yesBudget.isSelected())
            budget=1;
        else if (noBudget.isSelected())
            budget=0;
        if(yesTime.isSelected())
            time=1;
        else if (noTime.isSelected())
            time=0;
        reviewed.getSelectionModel().getSelectedItem();
        project.getSelectionModel().getSelectedItem();
        Review r1 = new Review(budget,time, (int) rating.getRating(),comment.getText());
        
        rs.UpdateReview(r1,r.getId());
        //reviewed.setValue("Select Freelancer");
        project.setValue("Select Project");
        yesBudget.setSelected(false);
        noBudget.setSelected(false);
        yesTime.setSelected(false);
        noTime.setSelected(false);
        rating.setRating(0);
        comment.setText("");
        tab.setItems(rs.AfficherReview());
        tab.setEditable(true);
        tab.refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Review updated with success!");
        alert.show();
        i=0;   
        count();
            
        }
    }
    
    @FXML
    public int update()  {
        i=1;
        rs=new ReviewService();
        Review r=tab.getSelectionModel().getSelectedItem();
        if(r.getOnBudget()==1){
        yesBudget.setSelected(true);
        noBudget.setSelected(false);} else
        {noBudget.setSelected(true);
        yesBudget.setSelected(false);}
        if(r.getOnTime()==1){
        yesTime.setSelected(true);
        noTime.setSelected(false);} else
        {noTime.setSelected(true);
        yesTime.setSelected(false);}
        rating.setRating(r.getRating());
        comment.setText(""+r.getComment());

       return i;
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        rs = new ReviewService();
        Review r=tab.getSelectionModel().getSelectedItem();
        rs.DeleteReview(r.getId());
        this.tab.setItems(rs.AfficherReview());
        tab.setEditable(true);
        tab.refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Review deleted with success!");
        alert.show();
        count();
    }
    
    public void count(){
        rs = new ReviewService();
        nbre.setText(""+rs.AfficherReview().size());
        
    }
    

}
