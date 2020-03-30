/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Report;

import Controllers.Report.*;
import Entities.Report;
import Services.Implementation.ReportService;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ReportController implements Initializable {

    @FXML
    private TextArea text;

    @FXML
    private Button add;
    
    @FXML
    private DatePicker createdAt;

    ReportService rps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createdAt.setValue(LocalDate.now());
    }    

    @FXML
    private void add(ActionEvent event) throws SQLException {

        rps = new ReportService();

        
        if (text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please add a comment!");
            alert.show();
            return;
        }
        
        java.sql.Date date = java.sql.Date.valueOf(createdAt.getValue());
        
        Report r = new Report(date,text.getText());
        rps.AjoutReport(r);
        
        text.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Report added with success!");
        alert.show();
    }
}
