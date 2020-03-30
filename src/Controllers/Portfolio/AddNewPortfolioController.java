/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Portfolio;

import Entities.Portfolio;
import Services.Implementation.PortfolioServices;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.O;

/**
 * FXML Controller class
 *
 * @author AGENT6
 */
public class AddNewPortfolioController implements Initializable {

    @FXML
    private TextField skillsId;
    @FXML
    private TextArea skillsId2;
    @FXML
    private TextArea descId;
    @FXML
    private TextArea preExId;
    
    ObservableList<Portfolio> ob;
    
     Document document=new Document();
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }   
    
    
    
    @FXML
     public void add(ActionEvent event) throws SQLException {
         PortfolioServices ps=new PortfolioServices();
         Portfolio p=new Portfolio(skillsId2.getText(), descId.getText(), preExId.getText());
         ps.addPortfolio(p,3);
        
       
           
            skillsId2.setText("");
            skillsId.setText("");
            descId.setText("");
            preExId.setText("");
               
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Portfolio added with success!");
        alert.show();
         
     }
     
     
      String skill="";
      
      
    @FXML
     public void addSkill()
     {
        
         skill+=skillsId.getText()+"\n";
         skillsId2.setText(skill);
         skillsId.setText("");
     }
     
     
     
     
     
     //Convert Portfolio To PDF File
     @FXML
    private void toPdf(ActionEvent event) throws IOException, DocumentException {
        PortfolioServices ps = new PortfolioServices();
        
    	try
        {
    		System.out.println("creating document");
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\AGENT6\\Desktop\\PDFPortfio.pdf"));
           document.open();
           //com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("src/cite/logo.png");
           //image1.setAbsolutePosition(490f, 715f);
           //image1.scaleAbsolute(100, 120);
           Paragraph Subject = new Paragraph("Subject: Portfolio  ");
           document.add(Subject);
           //document.set
                         Paragraph space = new Paragraph("\n");

                      document.add(space);
           document.add(space);
           document.add(space);
           document.add(space);
           document.add(space);
           //document.add(space);
            //double tot=0;
                Portfolio p=ps.ShowPortfolio(3);
               
                 Paragraph desc = new Paragraph("Skills: "+  "\n" + p.getSkills()+  "\n" +  "\n" +  "\n"  +  "Previous Experiences: "+  "\n"  +p.getPreviousExperiences()+  "\n" +  "\n" +  "\n"  +"Description: "+  "\n"  + p.getDescription());
                 
                 
                 
           document.add(space);
           document.add(desc);
       
            //document.add(space);
            //document.add(space);
            // Paragraph total=new Paragraph("Le totale est : "+tot+"dt");
           //document.add(total);
                   
          // document.add(desc);
           document.addAuthor("Pi");
       	   document.addCreationDate();
       	   document.addCreator("Souhail Hammadi");
       	   document.addTitle("Portfolio");
       	   document.addSubject("Subject.");
           document.close();
           writer.close();
           System.out.println("opening document");
           File file = new File("C:\\Users\\AGENT6\\Desktop\\PDFPortfio.pdf");
           System.out.println(file.getAbsolutePath());
           Desktop.getDesktop().open(file);
        }catch (DocumentException | IOException exception)
        {{System.out.println(exception);
        }
        // TODO Auto-generated catch block
        
        // TODO Auto-generated catch block

    
    }}
    
}
