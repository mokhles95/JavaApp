/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Job;

import Entities.FosUser;
import Entities.Job;
import Services.Implementation.JServices;
import com.jfoenix.controls.JFXComboBox;
import com.sun.javafx.scene.control.SelectedCellsMap;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AGENT6
 */
public class AllJobsInterfaceController implements Initializable {

    @FXML
    private TableView<Job> tab;

    @FXML
    private TextArea descId2;

    @FXML
    private TextField locId2;

    @FXML
    private TextField maxId2;

    @FXML
    private TextField minId2;


    @FXML
    private TextField titleId2;

    @FXML
    private JFXComboBox<String> typeId2;
    
     @FXML
    private TextField fullstack;

    @FXML
    private TextField internship;

    

    
    @FXML
    private TableColumn<Job, String> descId;

    @FXML
    private TableColumn<Job, String> locId;

    @FXML
    private TableColumn<Job, Double> maxId;

    @FXML
    private TableColumn<Job, Double> minId;

    @FXML
    private TableColumn<Job, String> titreId;

    @FXML
    private TableColumn<Job, String> typeId;
    
    @FXML
    private TableColumn<Job, Integer> id;

    
      @FXML
    private TextField total;
   
     
     JServices js;
     
     private ObservableList<Job> joblist;
     
    private ObservableList<Job> obs;
    
    private ObservableList<Job> obs2;
     
     
     int i=0;
    @FXML
    private Button saveId;
    @FXML
    private Label titreId3;
    @FXML
    private Label typeId3;
    @FXML
    private Label locId3;
    @FXML
    private Label minId3;
    @FXML
    private Label maxId3;
    @FXML
    private Label descId3;
    
    @FXML
    private TextField searchId;
    @FXML
    private JFXComboBox<String> searchById;
   

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     typeId2.getItems().addAll("Fullstack","Parttime","Internship"); 
     searchById.getItems().addAll("Type","Title","Location");
     searchById.setValue("Title");
     js=new JServices();
     joblist=FXCollections.observableArrayList();
     joblist=js.ShowJob();
      
     tableviewEvent(joblist);
     count();
     minId2.setText("0.0");
     maxId2.setText("0.0");
        hide();
       
       
        
    } 
    
    @FXML
    public int update()  {
        
        JServices js=new JServices();
        Job j=tab.getSelectionModel().getSelectedItem();
        if(j!=null)
        {
            show();
         i=1;
         titleId2.setText(j.getTitre());
        typeId2.setValue(null);
        minId2.setText(""+j.getMinSal());
        maxId2.setText(""+j.getMaxSal());
        descId2.setText(j.getDescription());
        locId2.setText(j.getLocation());
        
       }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);
        alert.setContentText("Please Select an Item to Update!");
        alert.show();
        }
        return i;
    }
    
    @FXML
    public void save(ActionEvent event) throws SQLException {
       JServices js=new JServices();
       
        if(tab.getSelectionModel().getSelectedItem()==null||i==0)
        {
            add();
        }
        else
        {                                   
            updated();
        }
        
    }
    
    //Controlleur::delete
    
    @FXML
   public void suppJob(ActionEvent event) {
        Job selectedJob=tab.getSelectionModel().getSelectedItem();
        if(selectedJob!=null)
        {
        js.deleteJob(selectedJob.getId());
        tab.setItems(js.ShowJob());
        tab.setEditable(true);
        tab.refresh();
        count();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);
        alert.setContentText("Please Select an Item to Delete!");
        alert.show();
        }
    }
    
    
    
    public void tableviewEvent(ObservableList<Job> o){
            
           
            
            tab.setItems(o);
          
         
          
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            id.setStyle("-fx-background-color:#E1E8ED");
            titreId.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            typeId.setCellValueFactory(new PropertyValueFactory<>("Type"));
            locId.setCellValueFactory(new PropertyValueFactory<>("Location"));
            minId.setCellValueFactory(new PropertyValueFactory<>("MinSal"));
            maxId.setCellValueFactory(new PropertyValueFactory<>("MaxSal"));
            descId.setCellValueFactory(new PropertyValueFactory<>("Description"));
            
            tab.refresh();
          
           
        
    }
    
    public void count()
    {
        total.setText(""+js.ShowJob().size()+"");
       internship.setText(""+js.ShowJobByTypeInternship().size()+"");
       fullstack.setText(""+js.ShowJobByTypeFullstack().size()+"");
    }
    
    
    
    
    
    
    private boolean isInputValid() 
    {
        String errorMessage = "";
        
        
        
        if (titleId2.getText() == null || titleId2.getText().length() == 0) 
        {
            errorMessage += "Invalid Title !\n"; 
        }
        if (typeId2.getValue() == null || typeId2.getValue().length() == 0 ) 
        {
            errorMessage += "Invalid Type !\n"; 
        }
        if (locId2.getText() == null || locId2.getText().length() == 0 ) 
        {
            errorMessage += "Invalid Location !\n"; 
        } 
        if (Double.parseDouble(minId2.getText())<0 )
        {
            errorMessage += "Invalid Minimum Salary !\n"; 
        }
        
       if (Double.parseDouble(maxId2.getText())<Double.parseDouble(minId2.getText())) 
        {          
            errorMessage += "Invalid Maximum Salary !\n"; 
        }
         if (descId2.getText() == null || descId2.getText().length() == 0 ) 
        {
            errorMessage += "Invalid Description !\n"; 
        } 
        
        if (errorMessage.length() == 0) 
        {
            return true;
        } else 
        {
        	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Credentials ");
			alert.setHeaderText("Please enter valid arguments");
			alert.setContentText(errorMessage);
			alert.showAndWait();

            return false;
        }
    }   
    
    public void add(){
       
        Job j=new Job(titleId2.getText(), typeId2.getValue(), locId2.getText(), Double.parseDouble(minId2.getText()), Double.parseDouble(maxId2.getText()), descId2.getText());
            if(isInputValid()){
        js.addJob(j,1);       
                
        this.tab.setItems(js.ShowJob());
        tab.setEditable(true);
            tab.refresh();
            titleId2.setText("");
            typeId2.setValue("");
            minId2.setText("0");
            maxId2.setText("0");
            descId2.setText("");
            locId2.setText("");        
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Job added with success!");
        alert.show();
        count();
                hide();
            }
    }
    
    public void updated()
    {
          Job j=tab.getSelectionModel().getSelectedItem();
        
        Job j1=new Job(j.getId(),titleId2.getText(), typeId2.getValue(), locId2.getText(), Double.parseDouble(minId2.getText()), Double.parseDouble(maxId2.getText()), descId2.getText());
        if(isInputValid()){
       js.updateJob(j1,j.getId());
       titleId2.setText("");
        typeId2.setValue("");
        minId2.setText("");
        minId2.selectAll();
        maxId2.setText("");
        descId2.setText("");
        locId2.setText("");
        count();
        tab.setEditable(true);
        tab.refresh();
        this.tab.setItems(js.ShowJob());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Job updated with success!");
        alert.show();
        i=0;               
        hide();
        count();
        }
    }
    
    
    public void hide(){
        typeId2.setVisible(false);
        titleId2.setVisible(false);
        maxId2.setVisible(false);
        minId2.setVisible(false);
        locId2.setVisible(false);
        descId2.setVisible(false);
        saveId.setVisible(false);
        typeId3.setVisible(false);
        titreId3.setVisible(false);
        maxId3.setVisible(false);
        minId3.setVisible(false);
        locId3.setVisible(false);
        descId3.setVisible(false);
        
        
    }
    
    
    @FXML
    public void show(){
        typeId2.setVisible(true);
        titleId2.setVisible(true);
        maxId2.setVisible(true);
        minId2.setVisible(true);
        locId2.setVisible(true);
        descId2.setVisible(true);
        saveId.setVisible(true);
        typeId3.setVisible(true);
        titreId3.setVisible(true);
        maxId3.setVisible(true);
        minId3.setVisible(true);
        locId3.setVisible(true);
        descId3.setVisible(true);
   
    }
     
    
    
    @FXML
    public void searchByTitle()
    {
        obs= FXCollections.observableArrayList();
        obs2= FXCollections.observableArrayList();
        JServices js1=new JServices();
        obs=js1.ShowJob();
        
        if(searchById.getValue().equals("Type"))
        {
              if(searchId.getText().equals(""))
        {
            
            tableviewEvent(obs);
        }
        else
        {
            for(int j=0;j<obs.size();j++)
            {
                String x=obs.get(j).getType().substring(0,searchId.getText().length()).toLowerCase();
                if(searchId.getText().toLowerCase().equals(x))
                {
                    obs2.add(obs.get(j));                  
                }
                 tableviewEvent(obs2);              
            }
        }
        }
         if(searchById.getValue().equals("Title"))
        {
              if(searchId.getText().equals(""))
        {
            tableviewEvent(obs);
        }
        else
        {
            for(int j=0;j<obs.size();j++)
            {
                String x=obs.get(j).getTitre().substring(0,searchId.getText().length()).toLowerCase();
                if(searchId.getText().toLowerCase().equals(x))
                {
                    obs2.add(obs.get(j));                  
                }
                 tableviewEvent(obs2);              
            }
        }
        }
        
        if(searchById.getValue().equals("Location"))
        {
              if(searchId.getText().equals(""))
        {
            tableviewEvent(obs);
        }
        else
        {
            for(int j=0;j<obs.size();j++)
            {
                String x=obs.get(j).getLocation().substring(0,searchId.getText().length()).toLowerCase();
                if(searchId.getText().toLowerCase().equals(x))
                {
                    obs2.add(obs.get(j));                  
                }
                 tableviewEvent(obs2);              
            }
        }
        } 
         
    }
}