/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Report;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;

/**
 *
 * @author Ahmed
 */
public class ReportService extends Report {

    private ObservableList<Event>data;
    private static ReportService reportServiceInstance;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    Connection cnx = DataSource.dbConnexion();   
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    Report report;
   
    public ReportService(){}
    
    public static ReportService getInstance(){
        if(reportServiceInstance==null) 
            reportServiceInstance=new ReportService();
        return reportServiceInstance;
    }
    
    public void AjoutReport(Report r) throws SQLException{
    try{
        String req ="INSERT INTO report (createdAt,text) Values(?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        
        st.setDate(1, (Date) r.getCreatedAt());
        st.setString(2, r.getText());
        st.executeUpdate();
        System.out.println("Report added");
    }
    catch (SQLException ex){
        Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE,null, ex);
        System.out.println(ex.getMessage());
    }
      
    }
    
 }
