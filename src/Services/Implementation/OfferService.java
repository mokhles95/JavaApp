/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

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
    

    
    private OfferService(){}
    
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
        obj.setPriceMax(rs.getInt("priceMax"));
        obj.setPriceMin(rs.getInt("priceMin"));
        obj.setLocation(rs.getString("location"));
        obj.setType(rs.getString("type"));
        obj.setStatus(rs.getString("status"));
        return obj;
    }

    @Override
    public List<Offer> getAll(Offer obj) throws SQLException {
String query = "select * from offer";
        ResultSet rs = cnx.createStatement().executeQuery(query);
        List<Offer> offers = new ArrayList<>();
        while(rs.next()){
            Offer offer = new Offer();
            offer.setId(rs.getInt("id"));
            offer.setName(rs.getString("name"));
            offer.setLocation(rs.getString("location"));
            offer.setType(rs.getString("status"));
            offer.setDescription(rs.getString("description"));
            offer.setPriceMax(rs.getInt("PriceMax"));
            offer.setPriceMin(rs.getInt("PriceMin"));

            offers.add(offer);
        }
        return offers;     }

    @Override
    public void create(Offer obj) throws SQLException {
        try{
            String query = "insert into offer(PriceMax, Description, Name, Type, Status, PriceMin ) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setDouble(1, obj.getId());
        pst.setDouble(2, obj.getPriceMax());
        pst.setString(3, obj.getDescription());
        pst.setString(5, obj.getLocation());
        pst.setString(6, obj.getName());
        pst.setString(7, obj.getType());
        pst.setString(8, obj.getStatus());
        pst.setDouble(9, obj.getPriceMin());          
        pst.executeUpdate();
      
                    }
         catch (SQLException ex){
             System.out.println("erreur create");

         }
               
    
    }
   
            
       

    @Override
    public void update(Offer obj) throws SQLException {
String query = "UPDATE offer SET PriceMax = ? , PriceMin = ? , Location = ? , Name = ?, Type = ?, Status = ?, Description = ? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setDouble(1, obj.getPriceMax());
        pst.setDouble(2, obj.getPriceMin());
        pst.setString(3, obj.getName());
        pst.setString(4, obj.getLocation());
        pst.setString(5, obj.getDescription());
        pst.setString(5, obj.getStatus());
        pst.setString(5, obj.getType());
        pst.executeUpdate();    }
    
    
}
