/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interface;

import Entities.Offer;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public interface OfferServiceInterface1 {
     public abstract Offer get(Offer obj) throws SQLException;
public ObservableList<Offer> OfferList();
public void create(Offer obj)throws SQLException;
   
     public void update(Offer obj , int x)throws SQLException;
      public void deleteOffer(int id);
     
    
}
