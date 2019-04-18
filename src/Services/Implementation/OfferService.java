/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Job;
import Entities.Offer;
import Services.Interface.OfferServiceInterface;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public class OfferService implements OfferServiceInterface{
    private static OfferService offerServiceInstance;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    Connection cnx = DataSource.dbConnexion();   
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    ObservableList<Offer> data;

    

    
    public OfferService(){}
    
    public static OfferService getInstance(){
        if(offerServiceInstance==null) 
            offerServiceInstance=new OfferService();
        return offerServiceInstance;
    }
    
    

    @Override
    public Offer get(Offer obj)throws SQLException {
        String query = "select * from Offer where id = "+obj.getId();
        ResultSet rs = cnx.createStatement().executeQuery(query);
        rs.next();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setDescription(rs.getString("description"));
        obj.setStatus(rs.getString("status"));
        obj.setPriceMax(rs.getInt("priceMax"));
        obj.setPriceMin(rs.getInt("priceMin"));
        obj.setLocation(rs.getString("location"));
        obj.setType(rs.getString("type"));
        
        
        return obj;

    
    
    } 
     
    
    
    @Override
    public ObservableList<Offer> OfferList(){
        ObservableList data;
        data =  FXCollections.observableArrayList();
        try{
            String req= "select * from Offer where status='En attente'";
            ResultSet rs=cnx.createStatement().executeQuery(req);
        
            while(rs.next())
            {
                Offer o=new Offer(rs.getInt("id"),rs.getString("name"),rs.getInt("priceMax"), rs.getString("type"));
                data.add(o); 
                
             
            }
            System.out.println(data.toString()) ;
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data;
     }
    
    
    
    
    public ObservableList<Offer> OfferListHistory(){
        ObservableList data;
        data =  FXCollections.observableArrayList();
        try{
            String req= "select * from Offer where status!='En attente'";
            ResultSet rs=cnx.createStatement().executeQuery(req);
        
            while(rs.next())
            {
                Offer o=new Offer(rs.getInt("id"),rs.getString("name"), rs.getString("type"),rs.getString("status"),rs.getString("location"),rs.getInt("priceMax"));
                data.add(o); 
                
             
            }
            System.out.println(data.toString()) ;
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data;
     }
    
    
    public ObservableList<Offer> AllOffer(){
        ObservableList data;
        data =  FXCollections.observableArrayList();
        try{
            String req= "select * from Offer";
            ResultSet rs=cnx.createStatement().executeQuery(req);
        
            while(rs.next())
            {
                Offer o=new Offer(rs.getInt("id"),rs.getString("name"), rs.getString("type"),rs.getString("status"),rs.getString("location"),rs.getInt("priceMax"));
                data.add(o); 
                
               
            }
            System.out.println(data.toString()) ;
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data;
     }


    @Override
    public void create(Offer obj)  {
        try{
            String query = "insert into offer(Name,PriceMin,PriceMax,Type,Description,Location,status) values(?, ?, ?, ?, ?, ? ,?)";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setString(1, obj.getName());
        pst.setDouble(2, obj.getPriceMin());
        pst.setDouble(3, obj.getPriceMax());
        pst.setString(4, obj.getType());
        pst.setString(5, obj.getDescription());
        pst.setString(6, obj.getLocation());
        pst.setString(7, "En attente");
            System.out.println("ajout");
                  
        pst.executeUpdate();
      
                    }
         catch (SQLException ex){
             System.out.println(ex.getMessage());
             

         }
               
    
    }
  
       

    @Override
    public void update(Offer obj, int x)  {
        try {
            String query = "UPDATE offer SET Name = ? ,Type = ? , Location = ?, PriceMax = ?  WHERE id ="+x ;
        PreparedStatement pst = cnx.prepareStatement(query);
       pst.setString(1, obj.getName());
        pst.setString(2, obj.getType());
        pst.setString(3, obj.getLocation());
        pst.setDouble(4, obj.getPriceMax());
       
        
       
        pst.executeUpdate(); 
            
        }
         catch (SQLException ex){
             System.out.println(ex.getMessage());
         }
        
        
        
   }
    
     

    @Override
    public void deleteOffer(int id) {

        PreparedStatement pt;
        
        try{
            String req="delete from Offer where id=?";
            pt=cnx.prepareStatement(req);
           
            pt.setInt(1,id);
          pt.executeUpdate();
           
     }
    catch(SQLException ex){}
    }
    
      
    public void updateAcceptedStatus(Offer obj, int x)  {
        try {
            String query = "UPDATE offer SET status=? WHERE id ="+x ;
        PreparedStatement pst = cnx.prepareStatement(query);
       pst.setString(1,"Accepted");
        
        pst.executeUpdate(); 
            
        }
         catch (SQLException ex){
             System.out.println(ex.getMessage());
         }
        
        
        
   }

    
    public void updateRejectedStatus(Offer obj, int x)  {
        try {
            String query = "UPDATE offer SET status=? WHERE id ="+x ;
        PreparedStatement pst = cnx.prepareStatement(query);
       pst.setString(1,"Rejected");
        
        pst.executeUpdate(); 
            
        }
         catch (SQLException ex){
             System.out.println(ex.getMessage());
         }
        
        
        
   }
 
    
    
    
}
