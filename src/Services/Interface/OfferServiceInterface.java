/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interface;

import Entities.Offer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface OfferServiceInterface {
     public abstract Offer get(Offer obj) throws SQLException;
     public List<Offer> getAll(Offer obj)throws SQLException;
     public void create(Offer obj)throws SQLException;
   
     public void update(Offer obj)throws SQLException;
     
    
}
